package com.mirkogrcic.utils.xml;

import com.mirkogrcic.gui.DataWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XmlDomUtils implements XmlUtils {
    private final static Logger logger = LoggerFactory.getLogger(DataWindow.class.getName());

    Document doc;
    public XmlDomUtils(Document doc) {
        this.doc = doc;
    }

    @Override
    public void updateByTagNamePath(String path, Object value) {
        if (!(value instanceof String)) {
            value = value.toString();
        }
        //String[] parts = path.split(".");
        //Node node = this.getNodeByTagNamePath(this.parsePath(path), this.doc);
        XmlPathSearch xmlPathSearch = new XmlPathSearch(path, doc);
        Node node = xmlPathSearch.search();
        if (node == null) {
            throw new RuntimeException(String.format("Could not find the requested path '%s'", path));
        }
        node.setTextContent((String) value);
    }

    private Node getNodeByTagNamePath(String[] path, Node node) {
        if (path.length == 0) {
            return node;
        }
        NodeList nodes = node.getChildNodes();
        Node resultNode;
        String name = path[0];
        if (name.startsWith("[")) {
            int index = Integer.parseInt(name.substring(1, name.length() - 1));
            return nodes.item(index);
        } else {
            for (int i = 0; i < nodes.getLength(); i++) {
                Node childNode = nodes.item(i);
                if (childNode.getNodeName().equals(name)) {
                    resultNode = this.getNodeByTagNamePath(Arrays.copyOfRange(path, 1, path.length), childNode);
                    if (resultNode != null) {
                        return resultNode;
                    }
                }
            }
        }
        return null;
    }
    private String[] parsePath(String path) {
        List<String> parts = new ArrayList<>();
        Matcher m = Pattern.compile("(\\[[0-9]+\\]|[a-zA-Z0-9]+)").matcher(path);
        while (m.find()) {
            parts.add(m.group());
        }

        String[] result = new String[parts.size()];
        System.arraycopy(parts.toArray(), 0, result, 0, parts.size());
        return result;
    }
    private String stringifyPath(String[] path) {
        StringBuilder builder = new StringBuilder();
        for (String item : path) {
            if (item.startsWith("[")) {
                builder.append(item);
            } else {
                if (builder.length() != 0) {
                    builder.append(".");
                }
                builder.append(item);
            }
        }
        return builder.toString();
    }
}

class XmlPathSearch {
    Document doc;
    String[] path;
    Node[] nodePath;
    int currentIndex = 0;
    Node currentNode;
    Node lastNode;

    public XmlPathSearch(String path, Document doc) {
        this.path = parsePath(path);
        this.doc = doc;
        this.nodePath = new Node[path.length()];
        this.currentNode = doc;
    }

    public Node search() {
        if (currentIndex >= path.length) {
            return currentNode;
        }
        NodeList nodes = currentNode.getChildNodes();
        Node resultNode;
        String name = path[currentIndex];
        if (name.startsWith("[")) {
            int index = Integer.parseInt(name.substring(1, name.length() - 1));
            int nodesFound = 0;
            for (int i = 0; i < nodes.getLength(); i++) {
                Node childNode = nodes.item(i);
                if (childNode.getNodeName().equals(lastNode.getNodeName())) {
                    nodesFound++;
                    if (nodesFound - 1 == index) {
                        this.lastNode = this.currentNode;
                        this.currentNode = childNode;
                        this.nodePath[this.currentIndex] = currentNode;
                        this.currentIndex++;
                        resultNode = this.search();
                        if (resultNode != null) {
                            return resultNode;
                        }
                    }
                }
            }
        } else {
            for (int i = 0; i < nodes.getLength(); i++) {
                Node childNode = nodes.item(i);
                if (childNode.getNodeName().equals(name)) {
                    this.lastNode = this.currentNode;
                    this.currentNode = childNode;
                    this.nodePath[this.currentIndex] = currentNode;
                    this.currentIndex++;
                    resultNode = this.search();
                    if (resultNode != null) {
                        return resultNode;
                    }
                }
            }
        }
        return null;
    }

    private String[] parsePath(String path) {
        List<String> parts = new ArrayList<>();
        Matcher m = Pattern.compile("(\\[[0-9]+\\]|[a-zA-Z0-9]+)").matcher(path);
        while (m.find()) {
            parts.add(m.group());
        }

        String[] result = new String[parts.size()];
        System.arraycopy(parts.toArray(), 0, result, 0, parts.size());
        return result;
    }
}
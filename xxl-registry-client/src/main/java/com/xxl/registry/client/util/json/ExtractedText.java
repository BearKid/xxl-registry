package com.xxl.registry.client.util.json;

public class ExtractedText {
    private String content;
    private int startIndex;
    private int endIndex;

    public ExtractedText(String content, int startIndex, int endIndex) {
        this.content = content;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    public String getContent() {
        return content;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }
}

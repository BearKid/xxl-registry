package com.xxl.registry.client.util.json;

/**
 * an extractor to extract content quoted by a pair of double quotes from text
 */
public class DoubleQuotesTextExtractor {
    private static final char DOUBLE_QUOTE = '"';
    private static final char BACK_SLASH = '\\';

    /**
     * extract the content quoted by the first pair of double quotes.
     * this means only one string would returned even the input contains more than one legal pairs of double quotes
     *
     * @param input text that may contains a pairs of double quotes
     */
    public ExtractedText extract(String input) {
        final int[] leftRight = determineIndicesOfFirstDoubleQuotesPair(input);
        return extractTextGivenStartEndIndicesOfDoubleQuotes(input, leftRight[0], leftRight[1]);
    }

    private int[] determineIndicesOfFirstDoubleQuotesPair(String input) {
        int leftIndex = -1;
        int rightIndex = -1;
        Character prevChar = null;
        for (int i = 0; i < input.length(); i++) {
            final char curChar = input.charAt(i);
            if (curChar == DOUBLE_QUOTE) {
                if (prevChar == null || prevChar != BACK_SLASH) {
                    if (leftIndex == -1) {
                        leftIndex = i;
                    } else {
                        rightIndex = i;
                    }
                }
            }
            if (leftIndex != -1 && rightIndex != -1) {
                break;
            }
            prevChar = curChar;
        }
        if (leftIndex == -1 || rightIndex == -1) {
            throw new IllegalArgumentException(
                String.format("input (%s) has wrong syntax, can not meet a right pair of double quotes", input)
            );
        }
        return new int[]{leftIndex, rightIndex};
    }

    /**
     * given a text input, extract content quoted by a pair of double quotes
     *
     * @param input      text contains a pair of double quotes
     * @param leftIndex  index of left double quote
     * @param rightIndex index of right double quote
     */
    private ExtractedText extractTextGivenStartEndIndicesOfDoubleQuotes(String input, int leftIndex, int rightIndex) {
        if (input.charAt(leftIndex) != DOUBLE_QUOTE) {
            throw new IllegalArgumentException(String.format("input (%s) not starts with \"", input));
        }

        if (input.charAt(rightIndex) != DOUBLE_QUOTE) {
            throw new IllegalArgumentException(String.format("input (%s) not ends with \"", input));
        }

        final int startIdx = leftIndex + 1;
        final int endIdx = rightIndex;
        final String content = input.substring(startIdx, endIdx);
        return new ExtractedText(content, startIdx, endIdx);
    }
}

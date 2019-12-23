package com.xxl.registry.client.test.util.json;

import com.xxl.registry.client.util.json.DoubleQuotesTextExtractor;
import com.xxl.registry.client.util.json.ExtractedText;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DoubleQuotesTextExtractorTest {

    @Test
    public void extract_should_return_extracted_content_given_input_is_a_string_wrapped_with_a_pair_of_double_quotes() {
        final String input = "\"hello-world\"";
        final ExtractedText extractedText = new DoubleQuotesTextExtractor().extract(input);
        assertThat(extractedText.getContent(), is("hello-world"));
        assertThat(extractedText.getStartIndex(), is(1));
        assertThat(extractedText.getEndIndex(), is(input.length() - 1));
    }

    @Test
    public void extract_should_return_extracted_content_given_input_eq_a_pair_of_double_quotes(){
        final ExtractedText extractedText = new DoubleQuotesTextExtractor().extract("\"\"");
        assertThat(extractedText.getContent(), isEmptyString());
        assertThat(extractedText.getStartIndex(), is(1));
        assertThat(extractedText.getEndIndex(), is(1));
    }

    @Test
    public void extract_should_return_extracted_content_given_input_contains_a_pair_of_double_quotes() {
        final String input = "this is a\"hello-world\" string";
        final ExtractedText extractedText = new DoubleQuotesTextExtractor().extract(input);
        assertThat(extractedText.getContent(), is("hello-world"));
        assertThat(extractedText.getStartIndex(), is(10));
        assertThat(extractedText.getEndIndex(), is(21));
    }

    @Test
    public void extract_should_return_extracted_content_given_input_contains_two_pairs_of_double_quotes() {
        final String input = "this is a\"hello-world\" string \"goodJob\"";
        final ExtractedText extractedText = new DoubleQuotesTextExtractor().extract(input);
        assertThat(extractedText.getContent(), is("hello-world"));
        assertThat(extractedText.getStartIndex(), is(10));
        assertThat(extractedText.getEndIndex(), is(21));
    }

    @Test
    public void extract_should_throw_exception_given_input_not_contains_a_pair_of_double_quotes(){
        try {
            new DoubleQuotesTextExtractor().extract("hello-world");
            Assert.fail("expected to throw Exception");
        }catch (Exception e){
            assertThat(e, is(instanceOf(IllegalArgumentException.class)));
        }

        try {
            new DoubleQuotesTextExtractor().extract("\"hello-world");
            Assert.fail("expected to throw Exception");
        }catch (Exception e){
            assertThat(e, is(instanceOf(IllegalArgumentException.class)));
        }

        try {
            new DoubleQuotesTextExtractor().extract("hello-world\"");
            Assert.fail("expected to throw Exception");
        }catch (Exception e){
            assertThat(e, is(instanceOf(IllegalArgumentException.class)));
        }

        try {
            new DoubleQuotesTextExtractor().extract("235\"hello-world");
            Assert.fail("expected to throw Exception");
        }catch (Exception e){
            assertThat(e, is(instanceOf(IllegalArgumentException.class)));
        }

        try {
            new DoubleQuotesTextExtractor().extract("hello-world\"356346");
            Assert.fail("expected to throw Exception");
        }catch (Exception e){
            assertThat(e, is(instanceOf(IllegalArgumentException.class)));
        }
    }
}
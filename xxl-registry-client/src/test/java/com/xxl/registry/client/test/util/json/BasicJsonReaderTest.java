package com.xxl.registry.client.test.util.json;

import com.xxl.registry.client.util.json.BasicJsonReader;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BasicJsonReaderTest {

    @Test
    public void parseMap_should_return_map_given_input_is_object_json() {
        final BasicJsonReader reader = new BasicJsonReader();
        final Map<String, Object> result = reader.parseMap("{\"goodsName\": \"这是商品\", \"id\": 123, \"price\": 0.52}");
        assertThat(result.size(), is(3));
        assertThat(result, Matchers.hasEntry("goodsName", (Object) "这是商品"));
        assertThat((Long) result.get("id"), is(123L));
        assertThat(result.get("price"), is((Object) 0.52D));
    }

    @Test
    public void parseMap_should_return_map_given_input_is_object_json_with_a_string_field_value_contains_colon() {
        final BasicJsonReader reader = new BasicJsonReader();
        final Map<String, Object> result = reader.parseMap("{ \"id\": 123, \"price\": 0.52, \"goodsName\": \"hello:world\"}");
        assertThat(result.size(), is(3));
        assertThat(result, Matchers.hasEntry("goodsName", (Object) "hello:world"));
        assertThat((Long) result.get("id"), is(123L));
        assertThat(result.get("price"), is((Object) 0.52D));
    }

    @Test
    public void parseMap_should_return_map_given_input_is_object_json_with_a_field_name_contains_colon() {
        final BasicJsonReader reader = new BasicJsonReader();
        final Map<String, Object> result = reader.parseMap("{ \"id\": 123, \"price\": 0.52, \"goods:Name\": \"hello-world\"}");
        assertThat(result.size(), is(3));
        assertThat(result, Matchers.hasEntry("goods:Name", (Object) "hello-world"));
        assertThat((Long) result.get("id"), is(123L));
        assertThat(result.get("price"), is((Object) 0.52D));
    }
}

package net.thisptr.jackson.jq.internal.misc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import net.thisptr.jackson.jq.internal.misc.JsonNodeComparator;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonNodeComparatorTest {

	@Test
	public void test() throws JsonProcessingException, IOException {
		final ObjectMapper mapper = new ObjectMapper();

		final JsonNode j3 = mapper.readTree("3");
		final JsonNode j10 = mapper.readTree("10");
		final JsonNode jhoge = mapper.readTree("\"hoge\"");

		final JsonNodeComparator sut = new JsonNodeComparator();
		assertTrue(sut.compare(j3, j10) < 0);
		assertTrue(sut.compare(j3, jhoge) < 0);
		assertTrue(sut.compare(j10, jhoge) < 0);

		final List<JsonNode> nodes = new ArrayList<>(Arrays.asList(j3, jhoge, j10));
		Collections.sort(nodes, sut);
		assertEquals(Arrays.asList(j3, j10, jhoge), nodes);
	}
}

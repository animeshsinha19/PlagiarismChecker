package server.plagiarism.engine;

import server.plagiarism.entity.EntityFactory;
import server.plagiarism.entity.File;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

/**
 * Test cases for TextDiffBasedEngine.
 *
 * @author Wen Qin
 */
public class TextDiffBasedEngineTest {
	// test for instance
	@Test
	public void instanceTest(){
		TextDiffBasedEngine engine = TextDiffBasedEngine.instance();
		TextDiffBasedEngine engine1 = TextDiffBasedEngine.instance();
		assertEquals(engine, engine1);
	}

	// test if one of the file is null
	@Test
	public void calculateSimilarityScoreNullFile() {
		File f1 = EntityFactory.getInstance().makeFile();
		f1.setName("f1");
		f1.setContent("public class JavaTest{}");

		TextDiffBasedEngine engine = TextDiffBasedEngine.instance();
		engine.calculateSimilarity(f1, null);
		assertEquals(engine.getSimilarityScore(), 0.0f, 0.0002);
	}

	// test if one of the file is null
	@Test
	public void calculateSimilarityScoreNullFile1() {
		File f1 = EntityFactory.getInstance().makeFile();;
		f1.setName("f1");
		f1.setContent("public class JavaTest{}");

		TextDiffBasedEngine engine = TextDiffBasedEngine.instance();
		engine.calculateSimilarity(null, f1);
		assertEquals(engine.getSimilarityScore(), 0.0f, 0.0002);
	}

	// test if both of the files are null
	@Test
	public void calculateSimilarityScoreTwoNullFiles() {
		TextDiffBasedEngine engine = TextDiffBasedEngine.instance();
		engine.calculateSimilarity(null, null);
		assertEquals(engine.getSimilarityScore(), 0.0f, 0.0002);
	}

	// test if files are the same
	@Test
	public void getSimilarityScoreSameFile() {
		File f1 = EntityFactory.getInstance().makeFile();
		f1.setName("JavaTest");
		f1.setContent("public class JavaTest{}");
		File f2 = EntityFactory.getInstance().makeFile();
		f2.setName("JavaTest");
		f2.setContent("public class JavaTest{}");

		TextDiffBasedEngine engine = TextDiffBasedEngine.instance();
		engine.calculateSimilarity(f1, f2);
		float score = engine.getSimilarityScore();
		assertEquals(score, 1.0f, 0.0002);

	}

	// test if files are partial different
	@Test
	public void getSimilarityScorePartialDifferentFile() {
		File f1 = EntityFactory.getInstance().makeFile();
		f1.setName("JavaTest");
		f1.setContent("public class JavaTest{int a;}");
		File f2 = EntityFactory.getInstance().makeFile();
		f2.setName("JavaTest");
		f2.setContent("public class JavaTest{int b;}");

		TextDiffBasedEngine engine = TextDiffBasedEngine.instance();
		engine.calculateSimilarity(f1, f2);
		float score = engine.getSimilarityScore();
		assertEquals(score, 0.5f, 0.0002);

	}

	// test if files are the same with different keywords
	@Test
	public void getSimilarityScoreSameFile1() {
		File f1 = EntityFactory.getInstance().makeFile();
		f1.setName("JavaTest");
		f1.setContent("package astNode;\n" +
				"public class Variable{ \n" +
				"private String variable; \n" +
				"public Variable(String v){ \n" +
				"variable = v;}}");
		File f2 = EntityFactory.getInstance().makeFile();
		f2.setName("JavaTest");
		f2.setContent("package astNode;\n" +
				"private class Variable{ \n" +
				"public int variable; \n" +
				"public Variable(int v){ \n" +
				"variable = v;}}");

		TextDiffBasedEngine engine = TextDiffBasedEngine.instance();
		engine.calculateSimilarity(f1, f2);
		float score = engine.getSimilarityScore();
		assertEquals(score, 1.0f, 0.0002);
	}

	// test if the two files are the same, complicated content
	@Test
	public void getSimilarityScoreSameFile2() throws IOException {
		String filePath = "src/test/resources/PlagiarismChecker-Data/set01/Sample1/LinkedList.java";
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		StringBuilder sb = new StringBuilder();
		String line = br.readLine();
		while (line != null) {
			sb.append(line);
			line = br.readLine();
		}
		br.close();

		File f1 = EntityFactory.getInstance().makeFile();;
		f1.setName("LinkedList");
		f1.setContent(sb.toString());

		File f2 = EntityFactory.getInstance().makeFile();;
		f2.setName("LinkedList");
		f2.setContent(sb.toString());

		TextDiffBasedEngine engine = TextDiffBasedEngine.instance();
		engine.calculateSimilarity(f1, f2);
		float score = engine.getSimilarityScore();
		assertEquals(score, 1.0f, 0.0002);
	}

	// test if one file is empty
	@Test
	public void getSimilarityScoreOneEmptyFile1() {
		File f1 = EntityFactory.getInstance().makeFile();
		File f2 = EntityFactory.getInstance().makeFile();
		f1.setName("JavaTest");
		f1.setContent("");
		f2.setName("JavaTest");
		f2.setContent("public class JavaTest1{}");

		TextDiffBasedEngine engine = TextDiffBasedEngine.instance();
		engine.calculateSimilarity(f1, f2);
		float score = engine.getSimilarityScore();
		assertEquals(score, 0.0f, 0.0002);
	}

	// test if one file is empty
	@Test
	public void getSimilarityScoreOneEmptyFile2() {
		File f1 = EntityFactory.getInstance().makeFile();
		File f2 = EntityFactory.getInstance().makeFile();
		f1.setName("JavaTest");
		f1.setContent("public class JavaTest1{}");
		f2.setName("JavaTest");
		f2.setContent("");

		TextDiffBasedEngine engine = TextDiffBasedEngine.instance();
		engine.calculateSimilarity(f1, f2);
		float score = engine.getSimilarityScore();
		assertEquals(score, 0.0f, 0.0002);
	}

	// test if two files are different
	@Test
	public void getSimilarityScoreDifferentFile() {
		File f1 = EntityFactory.getInstance().makeFile();
		File f2 = EntityFactory.getInstance().makeFile();
		f1.setName("JavaTest");
		f1.setContent("public class JavaTest{}");
		f2.setName("JavaTest1");
		f2.setContent("public class JavaTest1{}");

		TextDiffBasedEngine engine = TextDiffBasedEngine.instance();
		engine.calculateSimilarity(f1, f2);
		float score = engine.getSimilarityScore();
		assertEquals(score, 0.0f, 0.0002);
	}

	// test getResultSummary
	@Test
	public void getResultSummaryTest(){
		File f1 = EntityFactory.getInstance().makeFile();
		File f2 = EntityFactory.getInstance().makeFile();
		f1.setName("JavaTest");
		f1.setContent("public class JavaTest{}");
		f2.setName("JavaTest1");
		f2.setContent("public class JavaTest1{}");

		TextDiffBasedEngine engine = TextDiffBasedEngine.instance();
		engine.calculateSimilarity(f1, f2);
		assertEquals(engine.getResultSummary(), new ArrayList<String>());
	}
}

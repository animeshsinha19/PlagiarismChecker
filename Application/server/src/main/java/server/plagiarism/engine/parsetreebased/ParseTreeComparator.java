package server.plagiarism.engine.parsetreebased;

import com.github.javaparser.ast.CompilationUnit;

/**
 * Interface of the comparator for two parse trees
 * 
 * @author Wen Qin
 */
public interface ParseTreeComparator {
	
	/**
	 * Calculate the similarity between two parse trees
	 */
	public Float calculateSimilarity(CompilationUnit t1, CompilationUnit t2);
	
}

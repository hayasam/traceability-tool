package br.edu.ufcg.splab.testlink.data;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement(name = "testsuite")
@XmlSeeAlso({ br.edu.ufcg.splab.testlink.data.TestCase.class,
		br.edu.ufcg.splab.testlink.data.Keyword.class })
public class TestSuite implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4369665483486700399L;

	private String name;

	private List<TestSuite> testsuite;

	private List<TestCase> testcase;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TestSuite> getTestsuite() {
		return testsuite;
	}

	public void setTestsuite(List<TestSuite> testsuite) {
		this.testsuite = testsuite;
	}

	public List<TestCase> getTestcase() {
		return testcase;
	}

	public void setTestcase(List<TestCase> testcase) {
		this.testcase = testcase;
	}
	
	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append(String.format("<testsuite name='%s'>", this.getName()));
		str.append(System.lineSeparator());
		if (testsuite != null) {
			for (TestSuite ts: testsuite) {
				str.append(String.format("	%s", ts.toString()));
				str.append(System.lineSeparator());
			}
		}
		if (testcase != null) {
			for (TestCase tc: testcase) {
				str.append(String.format("	%s", tc.toString()));
				str.append(System.lineSeparator());
			}
		}
		str.append("<testsuite>");
		return str.toString();
	}

}
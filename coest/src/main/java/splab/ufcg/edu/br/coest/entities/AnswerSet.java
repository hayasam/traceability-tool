package splab.ufcg.edu.br.coest.entities;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;


@XmlRootElement(name = "answer_set")
@XmlSeeAlso({ 	splab.ufcg.edu.br.coest.entities.AnswerInfo.class, 
				splab.ufcg.edu.br.coest.entities.Link.class })
@XmlAccessorType(XmlAccessType.FIELD)
public class AnswerSet implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4985932980194369097L;

	@XmlElement
	private AnswerInfo answer_info;
	
	@XmlElementWrapper
    @XmlElement(name = "link")
	private List<Link> links;
	
	public AnswerSet(){
		
	}

	public AnswerInfo getAnswer_info() {
		return answer_info;
	}

	public void setAnswer_info(AnswerInfo answer_info) {
		this.answer_info = answer_info;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}


}

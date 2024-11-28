package many_to_many;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Authors {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int authorId;
	private String authorName;
	
	@ManyToMany
	List<Books> books;

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public List<Books> getBooks() {
		return books;
	}

	public void setBooks(List<Books> books) {
		this.books = books;
	}
}

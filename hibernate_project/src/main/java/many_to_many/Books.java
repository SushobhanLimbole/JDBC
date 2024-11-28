package many_to_many;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Books {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookId;
	private String bookName;
	
	@ManyToMany
	List<Authors> authors;

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public List<Authors> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Authors> authors) {
		this.authors = authors;
	}
}

package ja.domain;
import java.time.LocalDate;

public class Magazine {
	private int id;
	private String title;
	private String description;
	private LocalDate publishDate;
	private int subPrice;

	public Magazine(int id, String title, String description, LocalDate publishDate, int subPrice) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.publishDate = publishDate;
		this.subPrice = subPrice;
	}

	public Magazine(String title, String description, LocalDate publishDate, int subPrice) {
		this.title = title;
		this.description = description;
		this.publishDate = publishDate;
		this.subPrice = subPrice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(LocalDate publishDate) {
		this.publishDate = publishDate;
	}

	public int getSubPrice() {
		return subPrice;
	}

	public void setSubPrice(int subPrice) {
		this.subPrice = subPrice;
	}

	@Override
	public String toString() {
		if (id == 0)
			return "Magazine \"" + title + "\", " + description + ", publish date: " + publishDate + ", subscription price: " + subPrice / 100 + "." + String.format("%02d", subPrice % 100);
		else
			return "Magazine ID#" + id + ": Magazine \"" + title + "\", " + description + ", publish date: " + publishDate + ", subscription price: " + subPrice / 100 + "." + String.format("%02d", subPrice % 100);
	}
}
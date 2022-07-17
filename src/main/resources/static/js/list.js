// book class: represents a book
class Book {
	constructor(title, author, isbn) {
		this.title = title;
		this.author = author;
		this.isbn = isbn;
	}
}
// ui class: handle ui tasks
class UI {
	static displayBooks() {
		const StoredBooks = [
			{
				title: "Book One",
				author: "jane doe",
				isbn: "1234",
			},
			{
				title: "Book two",
				author: "jane doe",
				isbn: "1235",
			},
		];

		const books = StoredBooks;
		books.forEach((book) => UI.addBookToList(book));
	}

	static addBookToList(book) {
		const list = document.querySelector("#book-list");

		const row = document.createElement("tr");
		row.innerHTML = `<td>${book.title}</td><td>${book.author}</td><td>${book.isbn}</td><td><a href="#" class="btn btn-info btn-sm update">modificar</a></td><td><a href="#" class="btn btn-danger btn-sm delete">X</a></td>`;

		list.appendChild(row);
	}

	static deleteBook(el) {
		if (el.classList.contains("delete")) {
			el.parentElement.parentElement.remove();
			UI.showAlert("Book Removed", "success");
		} else if (el.classList.contains("update")) {
			document.querySelector("#title").value =
				el.parentElement.parentElement.getElementsByTagName("td")[0].innerHTML;
			document.querySelector("#author").value =
				el.parentElement.parentElement.getElementsByTagName("td")[1].innerHTML;
			document.querySelector("#isbn").value =
				el.parentElement.parentElement.getElementsByTagName("td")[2].innerHTML;
			el.parentElement.parentElement.remove();
		}
	}
	static showAlert(message, className) {
		const div = document.createElement("div");
		div.className = `alert alert-${className}`;
		div.appendChild(document.createTextNode(message));
		const container = document.querySelector(".container");
		const form = document.querySelector("#book-form");
		container.insertBefore(div, form);

		setTimeout(() => document.querySelector(".alert").remove(), 3000);
	}
	static clearFields() {
		document.querySelector("#title").value = "";
		document.querySelector("#author").value = "";
		document.querySelector("#isbn").value = "";
	}
}
// store class: handles storage

// event: display books
document.addEventListener("DOMContentLoaded", UI.displayBooks);
// event:add book
document.querySelector("#book-form").addEventListener("submit", (e) => {
	e.preventDefault();
	const title = document.querySelector("#title").value;
	const author = document.querySelector("#author").value;
	const isbn = document.querySelector("#isbn").value;

	if (title === "" || author === "" || isbn === "") {
		UI.showAlert("Please fill in all fieds", "danger");
	} else {
		const book = new Book(title, author, isbn);
		UI.addBookToList(book);

		UI.showAlert("Book Added", "success");
		UI.clearFields();
	}
});
// even: remove a book
document.querySelector("#book-list").addEventListener("click", (e) => {
	UI.deleteBook(e.target);
});

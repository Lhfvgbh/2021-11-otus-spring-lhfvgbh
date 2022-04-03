function loadBooks() {
    $.get('/api/v1/books').done(function (books) {
        $('#books > tbody').empty();
        books.forEach(function (book) {
            $('#books > tbody').append(`
                <tr>
                    <td>${book.title}</td>
                    <td>${book.description}</td>
                    <td>${book.author.penName}</td>
                    <td>${book.genre.name}</td>
                    <td>
                        <a href="#" onclick="editBook(${book.id})">Edit</a>
                        <a href="#" onclick="deleteBook(${book.id})">Delete</a>
                    </td>
                </tr>
            `)
        });
    })
}

function findBooksByTitle(title) {
    $.get('/api/v1/books/find/' + title).done(function (books) {
        $('#booksWithId > tbody').empty();
        books.forEach(function (book) {
            $('#booksWithId > tbody').append(`
                <tr>
                    <td>${book.id}</td>
                    <td>${book.title}</td>
                    <td>${book.description}</td>
                    <td>${book.author.penName}</td>
                    <td>${book.genre.name}</td>
                    <td>
                        <a href="#" onclick="editBook(${book.id})">Edit</a>
                        <a href="#" onclick="deleteBook(${book.id})">Delete</a>
                    </td>
                </tr>
            `)
        });
    })
}


function loadAuthors() {
    $.get('/api/v1/authors').done(function (authors) {
        $('#bookAuthorId').empty();
        authors.forEach(function (author) {
            $('#bookAuthorId').append(`
                <option value="${author.id}">${author.penName}</option>
            `)
        });
    })
}

function loadGenres() {
    $.get('/api/v1/genres').done(function (genres) {
        $('#bookGenreId').empty();
        genres.forEach(function (genre) {
            $('#bookGenreId').append(`
                <option value="${genre.id}">${genre.name}</option>
            `)
        });
    })
}

function dropEdition() {
    $('#bookId').val('');
    $('#bookTitle').val('');
    $('#bookDescription').val('');
    $('#bookAuthorId').val('');
    $('#bookGenreId').val('');
}

function showList() {
    $('#bookEditor').hide();
    $('#bookListByTitle').hide();
    dropEdition();
    loadBooks();
    $('#bookList').show();
}

function showSearch() {
    $('#bookEditor').hide();
    $('#bookList').hide();
    dropEdition();
    findBooksByTitle($('#bookName').val());
    $('#bookListByTitle').show();
}

function showEditor(id) {
    loadAuthors();
    loadGenres();
    $('#bookList').hide();
    $('#bookListByTitle').hide();
    $('#bookEditor').show();
}

function editBook(id) {
    showEditor(id);
    $.get('/api/v1/books/' + id).done(function (book) {
        $('#bookId').val(id);
        $('#bookTitle').val(book.title);
        $('#bookDescription').val(book.description);
        $('#bookAuthorId').val(book.author.id);
        $('#bookGenreId').val(book.genre.id);
    })
    dropEdition();
}

function saveBook() {
    let id = $('#bookId').val();
    let method = (id === '') ? 'POST' : 'PUT';
    $.ajax({
        url: '/api/v1/books',
        type: method,
        data: JSON.stringify({
            id: id,
            title: $('#bookTitle').val(),
            description: $('#bookDescription').val(),
            authorId: $('#bookAuthorId').val(),
            genreId: $('#bookGenreId').val()
        }),
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        success: function () {
            showList();
        }
    });
}

function deleteBook(id) {
    if (!confirm('Are you sure you want to delete this book?')) {
        return;
    }

    $.ajax({
        url: '/api/v1/books/' + id,
        type: 'DELETE',
        success: function () {
            loadBooks();
        }
    });
}
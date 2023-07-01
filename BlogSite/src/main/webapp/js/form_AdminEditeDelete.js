const tableElem = document.querySelector('.Edit-Delete');
const loginUser = document.querySelector('.nickName').innerHTML;
let count = 0;

document.querySelectorAll('.delete_btn').forEach((s) => {
	s.addEventListener('click', () => {
		count += 1;
		if (count === 1) {
			 tableElem.insertAdjacentHTML('beforebegin', '<div class="row ConfirmDeleteArticle">\n' +
																				'<div class="col-md-12 text-center">\n' +
																					'<p>Do you delete the article of "' + s.id + '"?</p> \n' +
																					'<button type="button" class="btn btn-light delete_cancel">Cancel</button>\n' +
																					'<a href="AdminServlet?action=delete&nickName=' + loginUser + '&articleTitle=' + s.id + '"' +
																						' class="btn btn-primary" role="button">Delete</a>\n' +
																				'</div>\n' +  
			 																'</div>');
			const deleteCancel = document.querySelector('.delete_cancel');
			deleteCancel.addEventListener('click', () => {
				document.querySelector('.ConfirmDeleteArticle').remove();
				count = 0;
			});
		}
	});
});




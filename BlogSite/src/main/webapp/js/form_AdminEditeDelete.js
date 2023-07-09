const formElem = document.getElementById('Create Form');
const loginUser = document.querySelector('.nickName').innerHTML.trim();
const tmpString = "";
let count = 0;

//Delete Article Method
document.querySelectorAll('.delete_btn').forEach((s) => {
	s.addEventListener('click', () => {
		count += 1;
		if (count === 1) {
			let deleteElem = '<div class="row ConfirmDeleteArticle">\n' +
				'<div class="col-md-12 text-center">\n' +
				'<p>Do you delete the article of "' + s.id + '"?</p> \n' +
				'<button type="button" class="btn btn-light delete_cancel">Cancel</button>\n' +
				'<a href="AdminServlet?action=delete&nickName=' + loginUser + '&articleTitle=' +
				s.id + '"' + ' class="btn btn-primary" role="button">Delete</a>\n' +
				'</div>\n' +
				'</div>';
			formElem.insertAdjacentHTML('afterbegin', deleteElem);

			const deleteCancel = document.querySelector('.delete_cancel');
			deleteCancel.addEventListener('click', () => {
				document.querySelector('.ConfirmDeleteArticle').remove();
				count = 0;
			});
		}
	});
});

//Edit Article Method
document.querySelectorAll('.edit_btn').forEach((s) => {
	s.addEventListener('click', () => {
		count += 1;
		if (count === 1) {
			let request = makeHttpObject();
			request.open("GET", s.name, true);
			request.responseType = "document";
			request.send(null);
			request.onreadystatechange = function () {
				if (request.readyState == 4 && request.status == 200) {
					//Make repeated contents
					const mainContents = request.response.querySelectorAll('.container Article section.mainContents');
					let repeatedContents = '';
					for (let i = 0; i < mainContents.length; i++) {
						repeatedContents +=
							'<div class="row my-2" id="Chapter' + i + '">\n' +
							//Chapter
							'<label class="col-md-2 col-form-label text-start" for="F_Chapter' + i + '">Chapter</label>\n' +
							'<div class="col-md-7">\n' +
							'<input type="text" class="form-control chapterData" id="F_Chapter' + i + '" name="chapter' + i +
							'" value="';
						if (mainContents[i].querySelector('h2.chapter') != null) {
							repeatedContents += mainContents[i].querySelector('h2.chapter').innerHTML + '" maxlength="100">\n';
						} else {
							repeatedContents += '" maxlength="100">\n';
						}
						repeatedContents += '</div>\n' +
							'</div>\n' +
							'<div class="row my-2">\n' +
							//Section 
							'<label class="col-md-2 col-form-label text-start" for="F_Section' + i + '">Section</label>\n' +
							'<div class="col-md-7">\n' +
							'<input type="text" class="form-control sectionData" id="F_Section' + i + '" name="section' + i +
							'" value="';
						if (mainContents[i].querySelector('h3.section') != null) {
							repeatedContents += mainContents[i].querySelector('h3.section').innerHTML + '" maxlength="100">\n';
						} else {
							repeatedContents += '" maxlength="100">\n';
						}
						repeatedContents += '</div>\n' +
							'</div>\n' +
							'<div class="row my-2">\n' +
							//Image File
							'<label class="col-md-2 col-form-label text-start" for="F_ImgPath' + i + '">Image file</label>\n' +
							'<div class="col-md-7">\n' +
							'<input type="file" class="form-control imgData" id="F_ImgPath' + i + '" accept=".jpg" name="image' + i +
							'" value="';
						if (mainContents[i].querySelector('img') != null) {
							repeatedContents += mainContents[i].querySelector('img').getAttribute('src') + '">\n';
						} else {
							repeatedContents += '">\n';
						}
						repeatedContents += '</div>\n' +
							'</div>\n' +
							'<div class="row my-2" id="ModifyPoint' + i + '">\n' +
							//Description
							'<label class="col-md-2 col-form-label text-start" for="F_Description' + i + '">Description</label>\n' +
							'<div class="col-md-7">\n' +
							'<textarea rows="6" class="form-control descriptionData" id="F_Description' + i + '" name="description' + i + '">';
						if (mainContents[i].querySelector('h5.description') != null) {
							repeatedContents += mainContents[i].querySelector('h5.description').innerHTML + '</textarea>\n';
						} else {
							repeatedContents += '</textarea>\n';
						}
						repeatedContents += '</div>\n' +
							'</div>\n';
					}

					//Get blog summary
					let blogSummary = '';
					if (request.response.querySelector('.container Article h5.summary') != null) {
						blogSummary = request.response.querySelector('.container Article h5.summary').innerHTML;
					}

					//Make edit form
					let formTag =
						'<div class="ConfirmEditArticle">\n' +
						'<div class="row my-2">\n' +
						//Blog Title
						'<label class="col-md-2 col-form-label text-start" for="F_Title">Blog Title*</label>\n' +
						'<div class="col-md-7">\n' +
						'<input type="text" class="form-control" id="F_Title" name="blogTitle" maxlength="100" ' +
						'value="' + request.response.querySelector('.container Article h1').innerHTML + '" required>\n' +
						'</div>\n' +
						'</div>\n' +
						'<div class="row my-2">\n' +
						//Main Image
						'<label class="col-md-2 col-form-label text-start" for="F_ImgPath">Image file*</label>\n' +
						'<div class="col-md-7">\n' +
						'<input type="file" class="form-control mainImg" id="F_ImgPath"	accept=".jpg" name="imageMain"' +
						'value="' + request.response.querySelector('img.mainImg').getAttribute('src') + '" required>\n' +
						'<label class="col-md-9 col-form-label text-start fw-bold">*means input required.</label>\n' +
					'</div>\n' +
						'</div>\n' +
						'<div class="row mb-4">\n' +
						//Blog Summary
						'<label class="col-md-2 col-form-label text-start" for="F_SummaryText">Blog summary</label>\n' +
						'<div class="col-md-7">\n' +
						'<textarea rows="3" class="form-control summary" id="F_SummaryText" name="summary">' +
						blogSummary + '</textarea>\n' +
						'</div>\n' +
						'</div>\n' +
						// Repeated Contents
						repeatedContents +
						'<input type="hidden" name="editProcess" value="edit" />\n' +
						'<input type="hidden" name="nickname" value="' + loginUser + '" />\n' +
						'<input type="hidden" name="articlePath" value="' + s.name + '" />\n' +
						'<div class="row my-4">\n' +
						'<div class="col-md-9 text-end edit_article">\n' +
						'<button type="submit" class="btn btn-primary edit_article">Edit</button>\n' +
						'<button type="button" class="btn btn-light edit_cancel">Cancel</button>\n' +
						'</div>\n' +
						'</div>\n' +
						'</div>\n';
					formElem.insertAdjacentHTML('afterbegin', formTag);

					//Click Cancel Button
					document.querySelector('button.edit_cancel').addEventListener('click', () => {
						document.querySelector('.ConfirmEditArticle').remove();
						count = 0;
					});
					/*Temporally Suspended
					formElem.insertAdjacentHTML('afterbegin',
					'<div class="ConfirmEditArticle2">\n' +
						'<div class="row mt-2">\n' +
							'<div class="col-md-6 text-center FormModify1">\n' +
								'<input type="image" src="img/logo_form_plus.svg" alt="Insert" class="Insert"' +
								' data-bs-toggle="tooltip" data-bs-placement="top" title="Append Items">\n' +
							'</div>\n' +
							'<div class="col-md-3 text-center FormModify2">\n' +
								'<input type="image" src="img/logo_form_minus.svg" alt="Delete"	class="Delete"' +
								' data-bs-toggle="tooltip" data-bs-placement="top" title="Delete Items">\n' +
							'</div>\n' +
						'</div>\n' +
					'</div>');								

					document.querySelector('div.FormModify1 input.Insert').addEventListener('click', appendElement(mainContents.length - 1));
					document.querySelector('div.FormModify2 input.Delete').addEventListener('click', deleteElement(mainContents.length - 1));
					*/
				}
			};
		}
	});
});

function makeHttpObject() {
	if ("XMLHttpRequest" in window) return new XMLHttpRequest();
	else if ("ActiveXObject" in window) return new ActiveXObject("Msxml2.XMLHTTP");
}


/*Temporally Suspended
function appendElement(number) {
	setTimeout(() => {
		let element = document.querySelector('#ModifyPoint');
		if (document.querySelector('#ModifyPoint' + number) !== null) {
			element = document.querySelector('#ModifyPoint' + number);
			number++;
		} 
		let newElement =
		`<div class="row my-2" id="Chapter` + number + `">
			<label class="col-md-2 col-form-label text-start" for="F_Chapter` + number + `">Chapter` + number + `</label>
			<div class="col-md-7">
				<input type="text" class="form-control" id="F_Chapter` + number + `" name="chapter` + number + `" maxlength="100">
			</div>
		</div>
		<div class="row my-2" id="Section` + number + `">
			<label class="col-md-2 col-form-label text-start" for="F_Section` + number + `">Section` + number + `</label>
			<div class="col-md-7">
				<input type="text" class="form-control" id="F_Section` + number + `" name="section` + number + `" maxlength="100">
			</div>
		</div>
		 <div class="row my-2" id="ImageFile` + number + `">
			<label class="col-md-2 col-form-label text-start" for="F_ImgPath` + number + `">Image file` + number + `</label>
			<div class="col-md-7">
				<input type="file" class="form-control" id="F_ImgPath` + number + `" accept=".jpg" name="image` + number + `">
			</div>
		</div>
		<div class="row my-2" id="ModifyPoint` + number + `">
			<label class="col-md-2 col-form-label text-start" for="F_Description` + number + `">Description` + number + `</label>
			<div class="col-md-7">
				<textarea rows="6" class="form-control" id="F_Description` + number + `" name="description` + number + `"></textarea>
			</div>
		</div>`;
		element.insertAdjacentHTML('afterend', newElement);
	}, 500);
}

function deleteElement(number) {
	setTimeout(() => {
		if (document.querySelector('#ModifyPoint' + number) === null && number >= 1) {
			number--;
		}
		if (number <= 1 && document.querySelector('#ModifyPoint1') === null) {
			number = 1;
			return;
		}
		document.querySelector('#Chapter' + number).remove();
		document.querySelector('#Section' + number).remove();
		document.querySelector('#ImageFile' + number).remove();       
		document.querySelector('#ModifyPoint' + number).remove();
	}, 500);
}*/
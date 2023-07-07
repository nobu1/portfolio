const tableElem = document.querySelector('.Edit-Delete');
const formElem = document.querySelector('form.EditForm');
const loginUser = document.querySelector('.nickName').innerHTML;
const tmpString = "";
let count = 0;

//Delete Article Method
document.querySelectorAll('.delete_btn').forEach((s) => {
	s.addEventListener('click', () => {
		count += 1;
		if (count === 1) {
			tableElem.insertAdjacentHTML('beforebegin',
			  '<div class="row ConfirmDeleteArticle">\n' +
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

//Edit Article Method
document.querySelectorAll('.edit_btn').forEach((s) => {
	s.addEventListener('click', () => {
		count += 1;
		if (count === 1) {
			let request = makeHttpObject();
			request.open("GET", s.name, true);
			request.responseType ="document";
			request.send(null);
			request.onreadystatechange = function() {
  				if (request.readyState == 4 && request.status == 200) {
  					const mainContents = request.response.querySelectorAll('.container Article section.mainContents');
 
  					let repeatedContents = '';
  					for (let i = 0; i < (mainContents.length - 1); i++) {  						
  						repeatedContents += 
  							'<div class="row my-2" id="Chapter' + i + '">\n' +
								'<label class="col-md-2 col-form-label text-start" for="F_Chapter' + i + '">Chapter</label>\n' +
								'<div class="col-md-7">\n' +
									//Chapter
									'<input type="text" class="form-control chapterData" id="F_Chapter' + i + '" name="chapter' + i +
									'" value="';
									if (mainContents[i].querySelector('h2.chapter')) {
										repeatedContents += mainContents[i].querySelector('h2.chapter').innerHTML + '" maxlength="100">\n';
									} else {
										repeatedContents += '" maxlength="100">\n';
									}
								repeatedContents += '</div>\n' +
							'</div>\n' +
							'<div class="row my-2">\n' +
								'<label class="col-md-2 col-form-label text-start" for="F_Section' + i + '">Section</label>\n' +
								'<div class="col-md-7">\n' +
									//Section 
									'<input type="text" class="form-control sectionData" id="F_Section' + i + '" name="section' + i +
									'" value="';
									if (mainContents[i].querySelector('h3.section')) {
										repeatedContents +=  mainContents[i].querySelector('h3.section').innerHTML + '" maxlength="100">\n';
									} else {
										repeatedContents += '" maxlength="100">\n';
									}
								repeatedContents += '</div>\n' +
							'</div>\n' +
							'<div class="row my-2">\n' +
								'<label class="col-md-2 col-form-label text-start" for="F_ImgPath' + i + '">Image file</label>\n' +
								'<div class="col-md-7">\n' +
									//Image File
									'<input type="file" class="form-control imgData" id="F_ImgPath' + i + '" accept=".jpg" name="image' + i +
									'" value="';
									if (mainContents[i].querySelector('img').getAttribute('src')) {
										repeatedContents += mainContents[i].querySelector('img').getAttribute('src') +  '">\n';
									} else {
										repeatedContents += '">\n';
									}
									repeatedContents += '<div id="mainImgHelp" class="form-text">If you change the image, please input a file.</div>\n' +
								'</div>\n' +
							'</div>\n' +
							'<div class="row my-2" id="ModifyPoint' + i + '">\n' +
								'<label class="col-md-2 col-form-label text-start" for="F_Description' + i + '">Description</label>\n' +
								'<div class="col-md-7">\n' +
									//Description
									'<textarea rows="6" class="form-control descriptionData" id="F_Description' + i + '" name="description' + i + '">';
									if (mainContents[i].querySelector('h5.description')) {
										repeatedContents += mainContents[i].querySelector('h5.description').innerHTML + '</textarea>\n';
									} else {
										repeatedContents +=  '</textarea>\n';
									}
								repeatedContents += '</div>\n' +
							'</div>\n';
  					}
  					
  					let blogSummary = '';
  					if (request.response.querySelector('.container Article h5.summary')) {
  						blogSummary = request.response.querySelector('.container Article h5.summary').innerHTML;
  					} 
  								
  				 	tableElem.insertAdjacentHTML('afterbegin',  				 	
					'<form id="EditForm">' +
					'<div class="ConfirmEditArticle">\n' +
						'<div class="row my-2">\n' +
							'<label class="col-md-2 col-form-label text-start" for="F_Title">Blog Title</label>\n' +
							'<div class="col-md-7">\n' +
								//Blog Title
								'<input type="text" class="form-control" id="F_Title" name="blogTitle" maxlength="100" ' + 
								'value="' + request.response.querySelector('.container Article h1').innerHTML + '" required>\n' +
							'</div>\n' +
						'</div>\n' +
						'<div class="row my-2">\n' +
							'<label class="col-md-2 col-form-label text-start" for="F_ImgPath">Image file</label>\n' +
							'<div class="col-md-7">\n' +
								//Main Image
								'<input type="file" class="form-control mainImg" id="F_ImgPath"	accept=".jpg" name="imageMain"' +
								'value="' + request.response.querySelector('img.mainImg').getAttribute('src') + '">\n' +
								'<div id="mainImgHelp" class="form-text">If you change the image, please input a file.</div>\n' +
							'</div>\n' +
						'</div>\n' +
						'<div class="row mb-4">\n' +
							'<label class="col-md-2 col-form-label text-start" for="F_SummaryText">Blog summary</label>\n' +
							'<div class="col-md-7">\n' +
								//Blog Summary
								'<textarea rows="3" class="form-control summary" id="F_SummaryText" name="summary">' +
								blogSummary + '</textarea>\n' +
							'</div>\n' +
						'</div>\n' +
						// Repeated Contents
						repeatedContents +
						'<div class="row my-4">\n' +
							'<div class="col-md-9 text-end edit_article">\n' +
								'<button type="button" class="btn btn-primary edit_article">Edit</button>\n' +
								'<button type="button" class="btn btn-light edit_cancel">Cancel</button>\n' +
							'</div>\n' +
						'</div>\n' +
					'</div>\n' +
					'</form>');
					
					/*Temporally Suspended
					tableElem.insertAdjacentHTML('afterbegin',
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
					
					document.querySelector('button.edit_article').addEventListener('click', () => {
						//Get blog title
						let title = "";
						if (document.forms.EditForm.elements[0].value?.trim()) {
							title = document.forms.EditForm.elements[0].value;
						} else {
							title = document.getElementById('F_Title').getAttribute('value');
						}
						
						//Get main image
						let imgLists = new Array();
						if (document.forms.EditForm.elements[1].value?.trim()) {
							imgLists.push(document.forms.EditForm.elements[1].value);
						} else {
							imgLists.push(document.getElementById('F_ImgPath').getAttribute('value'));
						}				
						
						//Get blog summary
						let summary = "";
						if (document.forms.EditForm.elements[2].value?.trim()) {
							summary = document.forms.EditForm.elements[2].value;
						} else {
							summary = "";
						}

						let chapterLists = new Array();
						let sectionLists = new Array();
						let descriptionLists = new Array();

						if ((document.forms.EditForm.elements.length -2) > 3) {
							//Get first chapter
							if (document.forms.EditForm.elements[3].value?.trim()) {
								chapterLists.push(document.forms.EditForm.elements[3].value);
							} else {
								chapterLists.push(document.getElementById('F_Chapter0').getAttribute('value'));
							}
						}
						
						if ((document.forms.EditForm.elements.length - 2) > 4) {
							//Get first section
							if (document.forms.EditForm.elements[4].value?.trim()) {
								sectionLists.push(document.forms.EditForm.elements[4].value);
							} else {
								sectionLists.push("");
							}
						}
						
						if ((document.forms.EditForm.elements.length - 2) > 5) {
							//Get second image
							if (document.forms.EditForm.elements[5].value?.trim()) {
								imgLists.push(document.forms.EditForm.elements[5].value?.trim());
							} else {
								imgLists.push(document.getElementById('F_ImgPath0').getAttribute('value'));
							}
						}
						
						if ((document.forms.EditForm.elements.length -2) > 6) {
							//Get first description
							if (document.forms.EditForm.elements[6].value?.trim()) {
								descriptionLists.push(document.forms.EditForm.elements[6].value);
							} else {
								descriptionLists.push("");
							}
						}

						//Get repeated items
						if ((document.forms.EditForm.elements.length -2) > 7) {
							for (let i = 0; i < (mainContents.length - 2); i++) {
								//Chapters
								if (document.forms.EditForm.elements[(i + 7)].value?.trim()) {
									chapterLists.push(document.forms.EditForm.elements[(i + 7)].value);
								} else {
									chapterLists.push("");
								}

								//Sections
								if (document.forms.EditForm.elements[(i + 8)].value?.trim()) {
									sectionLists.push(document.forms.EditForm.elements[(i + 8)].value);
								} else {
									sectionLists.push("");
								}

								//Images
								let imgParam = 'F_ImgPath' + (i + 1);
								if (document.forms.EditForm.elements[(i + 9)].value?.trim()) {
									imgLists.push(document.forms.EditForm.elements[(i + 9)].value);
								} else if (document.getElementById(imgParam).getAttribute('value')?.trim()) {
									imgLists.push(document.getElementById(imgParam).getAttribute('value'));
								} else {
									imgLists.push("");
								}
							
								//Descriptions
								if (document.forms.EditForm.elements[(i + 10)].value?.trim()) {
									descriptionLists.push(document.forms.EditForm.elements[(i + 10)].value);
								} else {
									descriptionLists.push("");
								}
							}
						}				
						
						//Send article data to EditServlet.java
						let sendData = new XMLHttpRequest();
						sendData.open('POST', document.querySelector('p.EditServlet').getAttribute('id'));
						sendData.setRequestHeader('content-type', 'application/x-www-form-urlencoded');									
						sendData.send('blogTitle='+title+'&imgLists='+imgLists+'&blogSummary='+summary+
									  '&chapterLists='+chapterLists+'&sectionLists='+sectionLists+
									  '&descriptionLists='+descriptionLists);
						document.getElementById('EditForm').remove();
						count = 0;
					});
					
					document.querySelector('button.edit_cancel').addEventListener('click', () => {
						document.getElementById('EditForm').remove();
						count = 0;
					});			
				}
			};
		}
	});
});

function makeHttpObject() {
  if("XMLHttpRequest" in window) return new XMLHttpRequest();
	else if("ActiveXObject" in window) return new ActiveXObject("Msxml2.XMLHTTP");
}



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
}
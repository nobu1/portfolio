let count = 1;

document.querySelector('div.FormModify1 input.Insert').addEventListener('click', appendElement);
document.querySelector('div.FormModify2 input.Delete').addEventListener('click', deleteElement);

function appendElement() {
    setTimeout(() => {
        let element = document.querySelector('#ModifyPoint');
        if (document.querySelector('#ModifyPoint' + count) !== null) {
            element = document.querySelector('#ModifyPoint' + count);
            count++;
        } 
        let newElement =
        `<div class="row my-2" id="Chapter` + count + `">
            <label class="col-md-3 col-form-label text-start" for="F_Chapter` + count + `">Chapter` + count + `</label>
            <div class="col-md-9">
                <input type="text" class="form-control" id="F_Chapter` + count + `" name="chapter` + count + `" maxlength="100">
            </div>
        </div>
        <div class="row my-2" id="Section` + count + `">
            <label class="col-md-3 col-form-label text-start" for="F_Section` + count + `">Section` + count + `</label>
            <div class="col-md-9">
                <input type="text" class="form-control" id="F_Section` + count + `" name="section` + count + `" maxlength="100">
            </div>
        </div>
        <div class="row my-2" id="ImageFile` + count + `">
            <label class="col-md-3 col-form-label text-start" for="F_ImgPath` + count + `">Image file` + count + `</label>
            <div class="col-md-9">
                <input type="file" class="form-control" id="F_ImgPath` + count + `" accept=".jpg" name="image` + count + `">
            </div>
        </div>
        <div class="row my-2" id="ModifyPoint` + count + `">
            <label class="col-md-3 col-form-label text-start" for="F_Description` + count + `">Description` + count + `</label>
            <div class="col-md-9">
                <textarea rows="6" class="form-control" id="F_Description` + count + `" name="description` + count + `"></textarea>
            </div>
        </div>`;
        element.insertAdjacentHTML('afterend', newElement);
    }, 500);
}

function deleteElement() {
    setTimeout(() => {
        if (document.querySelector('#ModifyPoint' + count) === null && count >= 1) {
            count--;
        }
        if (count <= 1 && document.querySelector('#ModifyPoint1') === null) {
            count = 1;
            return;
        }
        document.querySelector('#Chapter' + count).remove();
        document.querySelector('#Section' + count).remove();
        document.querySelector('#ImageFile' + count).remove();       
        document.querySelector('#ModifyPoint' + count).remove();
    }, 500);
}
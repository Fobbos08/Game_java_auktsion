<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="tovarForm">
    <form id="addTovar" name="addTovar" action="#">
        <label class="element">Title:</label><input class="element" id="title" name="title" type="text"/>
        <label class="element">Description:</label><input class="element" id="description" name="description" type="text"/>
        <input type="file" id="inputFile">
        <div id="imageBox" class="imageBox"></div>
        <input class="element" type="submit">
    </form>
</div>
<script type="text/javascript">
    function handleImage(image) {
        var reader = new FileReader();
        reader.onload = function(event) {

            var img = new Image();
            img.onload = function() {
                img.style.width = "100%";
                img.style.height = "100%";
                $("#imageBox")[0].innerHTML = img.outerHTML;
            }
            img.src = event.target.result;

        }

        reader.readAsDataURL(image);
    }

    function getFile(fileInput) {
        var fileObj;
        if (typeof ActiveXObject == "function") { // IE
            fileObj = (new ActiveXObject("Scripting.FileSystemObject")).getFile(fileInput.value);
        } else {
            fileObj = fileInput.files[0];
        }
        if (fileObj.type.indexOf("image") >= 0 &&
                (fileObj.type.indexOf("jpeg") >= 0 ||
                fileObj.type.indexOf("png") >= 0 ||
                fileObj.type.indexOf("gif") >= 0) &&
                fileObj.size <= instagramAttachFileSize * 1024 * 1024) {
            handleImage(fileObj);
        }
    }

    $('#inputFile').change(function() {
        getFile(this);
    });
</script>

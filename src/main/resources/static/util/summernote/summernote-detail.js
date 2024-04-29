$(document).ready(function() {
    var content = $('#summernote').val();
    $('#summernote').val('')
    $('#summernote').summernote('destroy');
    $('#summernote').after('<div id="readonlyContent" class="board"></div>');
    $('#readonlyContent').html(content);
});
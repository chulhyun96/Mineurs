$(document).ready(function() {
    $('#domain-select').change(function() {
        var domainValue = $(this).val();
        if (domainValue !== '직접입력') {
            $('input[name="domainpart"]').val(domainValue);
        } else {
            $('input[name="domainpart"]').val('');
        }
    });
});

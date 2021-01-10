let home = {
    init: function () {
        let _this = this;

        $('#url').on('keypress', function (e) {
            if (e.key === 'Enter') {
                _this.search();
            }
        });

        $('#btn-search').on('click', function () {
            _this.search();
        });
    },
    loading: function (isLoading) {
        let html = '';

        if (isLoading) {
            html += `<div id="loading">`;
            html += `<i class="fas fa-spinner fa-spin fa-2x fa-fw" style="margin-bottom: 10px"></i>`;
            html += `<p class="is-size-6">Loading...</p>`;
            html += `</div>`;
        } else {
            html = '';
        }

        $('#result').html(html);
    },
    search: function () {
        let html = '';
        let url = $('#url').val();
        this.loading(true);

        $.ajax({
            type: 'post',
            url: `/api/embed?url=${url}`,
            dataType: 'text',
            contentType: 'text; charset=utf-8',

        }).done(function (response) {
            let data = JSON.parse(response);

            html += `<table id="success" class="table is-hoverable is-fullwidth" style="word-break: break-all">`;
            html += `<tbody>`;
            $.each(data, function (k, v) {
                html += `<tr>`;
                html += `<td>${k}</td>`;
                (k === 'thumbnail_url')
                    ? html += `<td><img alt="thumbnail_url" src=${v}></td>`
                    : html += `<td>${v}</td>`;
                html += `</tr>`;
            });
            html += `</tbody>`;
            html += `</table>`;

        }).fail(function (error) {
            let data = JSON.parse(error.responseText);

            html += `<div id="error">`;
            html += `<span>${data.message}</span>`;
            html += `</div>`;

        }).always(function () {
            home.loading(false);

            $('#result').html(html);
        })
    }
};

home.init();
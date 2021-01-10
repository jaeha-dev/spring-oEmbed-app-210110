const urls = [
    { name: 'twitter', url: 'https://twitter.com/hellopolicy/status/867177144815804416', },
    { name: 'instagram', url: 'https://www.instagram.com/p/BUawPlPF_Rx', },
    { name: 'facebook', url: 'https://www.facebook.com/CDC/photos/a.184668026025/10158766067016026', },
    { name: 'vimeo', url: 'https://vimeo.com/20097015', },
    { name: 'naver', url: 'https://audioclip.naver.com/channels/108/clips/2', },
    { name: 'kakao', url: 'https://tv.kakao.com/channel/3659901/cliplink/415434551', },
    { name: 'youtube', url: 'https://www.youtube.com/watch?v=dBD54EZIrZo', },
];

let tags = {
    init: function () {
        let _this = this;

        $('#supported span').on('click', function () {
            let name = $(this).attr('id');

            _this.click(name);
        });
    },
    click: function (name) {
        // urls: (home.js)
        let url = urls.find(o => o.name === name).url;

        $('#url').val(url);
    }
};

tags.init();
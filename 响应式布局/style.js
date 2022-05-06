window.onload = function () {
  var imgs = document.getElementsByTagName("img");
  var body = document.getElementsByTagName("body")[0];
  for (let index = 1; index < imgs.length; index++) {
    imgs[index].onmouseover = function () {
      body.style.backgroundImage = "url(" + imgs[index].src + ")";
    };
    imgs[index].onmouseleave = function () {
      body.style.background = "";
    };
  }
};

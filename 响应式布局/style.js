window.onload = function () {
  var imgs = document.getElementsByTagName("img");
  var width = imgs[0].style.width;
  var marginLeft = imgs[0].style.marginLeft;
  for (let index = 0; index < imgs.length; index++) {
    imgs[index].onmouseover = function () {
      this.style.width = "100%";
      this.style.marginLeft = "0%";
    };
    imgs[index].onmouseleave = function () {
      this.style.width = width;
      this.style.marginLeft = marginLeft;
    };
  }
};

window.onload = function () {
  var imgs = document.getElementsByTagName("img");
  var width = imgs[1].style.width;
  var marginLeft = imgs[1].style.marginLeft;
  for (let index = 1; index < imgs.length; index++) {
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

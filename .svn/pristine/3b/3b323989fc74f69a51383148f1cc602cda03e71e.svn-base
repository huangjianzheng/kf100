import CanvasCompress from 'canvas-compress';

export async function compressImage(file) {
  let {width, height} = await getImageWidthAndHeight(file);
  const compressor = new CanvasCompress({
    type: CanvasCompress.MIME.JPEG,
    width: width,
    height: height,
    quality: 0.5,
  });

  let {source, result} = await compressor.process(file);
  //只要在这里返回一个立即resolve的Promise对象，压缩后的文件作为参数即可实现压缩
  console.log('compress', result);
  file.status = "ready";
  return Promise.resolve(result.blob);
}

function getImageWidthAndHeight(file) {
  return new Promise((resolve, reject) => {
    let reader = new FileReader();
    reader.onload = function (e) {
      let data = e.target.result;
      let img = new Image();
      img.onload = function () {
        resolve({
          width: img.width,
          height: img.height
        })
      };
      img.src = data;
    };
    reader.readAsDataURL(file)
  })
}

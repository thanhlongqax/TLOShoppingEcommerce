function updateQuantity(change) {
    var inputElement = document.getElementById('quantityInput');
    var currentValue = parseInt(inputElement.value, 10);
    var newValue = currentValue + change;

    // Ensure the quantity does not go below 1
    newValue = Math.max(1, newValue);

    inputElement.value = newValue;
}
function addCart(product) {
    // Lấy giá trị từ các thành phần HTML
    const productId = document.getElementById('productId').innerText;
    const hinhSp = (document.getElementById('productImage').src+ "").split("/").pop();
    const tenSp = document.getElementById('productName').innerText;
    const giaSp = document.getElementById('productPrice').innerText;
    const chiTietSp = document.getElementById('productDescription').innerText;
    const quantity = document.getElementById('quantityInput').value;
    const brandSp = document.getElementById('productBrand').innerText;
    const colorSp = document.getElementById('productColor').innerText;
    const Sp = {
        id: parseInt(productId),
        picture: hinhSp,
        name: tenSp,
        price: parseInt(giaSp, 10),
        detailProduct : chiTietSp,
        quality :quantity,
        brand: brandSp,
        color: colorSp,
    };
    gioHang = JSON.parse(sessionStorage.getItem("gioHang")) || [];
    const existingProductIndex = gioHang.findIndex((item) => item.id === parseInt(productId));

    if (existingProductIndex !== -1) {
        gioHang[existingProductIndex].quality++;
    } else {
        gioHang = [...gioHang, Sp];
    }
    sessionStorage.setItem("gioHang", JSON.stringify(gioHang));
    window.location.href = "/HomePage";
}


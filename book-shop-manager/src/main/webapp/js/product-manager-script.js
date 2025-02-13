// let products = [];
// const rowsPerPage = 10;
// let currentPage = 1;
//
// let currentProductId = -1;
//
// fetch("http://localhost:8080/products")
//     .then(response => response.json())
//     .then(data => {
//         products = data;
//         renderHomeProduct();
//         renderProducts();
//         console.log("trong fetch " ,products);
//
//     })
//     .catch(error => console.error("Lỗi khi gọi API:", error));
// console.log("full list",products);
// //hien thi all product admin
// function renderProducts(data) {
//             if (data === undefined){
//                 data = products;
//             }
//             data.sort((a, b) => a.id - b.id);
//             const tbody = document.getElementById("productTable");
//             tbody.innerHTML = ""; // Xóa nội dung cũ
//
//             const start = (currentPage - 1) * rowsPerPage;
//             const end = start + rowsPerPage;
//             const paginatedItems = data.slice(start, end);
//             console.log("Current Page:", currentPage);
//             console.log("Paginated Items:", paginatedItems);
//
//             paginatedItems.forEach((product,index) => {
//                 const row= `
//                     <tr>
//                         <th scope="row">${index + 1}</th>
//                         <td>${product.id}</td>
//                         <td>${(product.name)}</td>
//                         <td>${(product.author)}</td>
//                         <td>${product.pages}</td>
//                         <td>${(product.publisher)}</td>
//                         <td>${(product.categoryName)}</td>
//                         <td>${product.yearPublishing}</td>
//                         <td>${product.totalBuy}</td>
//                         <td class="text-center text-nowrap">
//                             <a class="btn btn-primary me-2" href="/view-product?id=${product.id}">Xem</a>
//                             <a class="btn btn-success me-2 btn-insert-to-update-form" href="/form-product?id=${product.id}" >Sửa</a>
//                             <a class="btn btn-danger" href="/delete-product?id=${product.id}"
//                                onclick="return confirm('Bạn chắc chắn muốn xóa?')">Xóa</a>
//                         </td>
//                     </tr>
//                 `;
//
//                 tbody.innerHTML += row;
//
//             });
//     renderPagination();
//
// }
//
// //phan trang
// function renderPagination() {
//     const totalPages = Math.ceil(products.length / rowsPerPage);
//     const pagination = document.getElementById("pagination");
//
//     pagination.innerHTML = `
//      <li class="page-item ${currentPage === 1 ? "disabled" : ""}" id="prev-page">
//         <a class="page-link" href="#">Previous</a>
//     </li>
//     `;
//     for(let i=1; i<=totalPages;i++){
//         pagination.innerHTML +=`
//         <li class = "page-item ${i === currentPage ? 'active' : ''}">
//         <a class="page-link" href="#">${i}</a>
//         </li>
//         `;
//     }
//     pagination.innerHTML += `
//     <li class="page-item ${currentPage === totalPages ? "disabled" : ""}" id="next-page">
//        <a class="page-link" href="#">Next</a>
//    </li>
//    `;
//     attachPaginationEvents();
//
// }
//
// function attachPaginationEvents() {
//     document.querySelectorAll(".pagination .page-item a").forEach(button => {
//         button.addEventListener("click", function (e) {
//             e.preventDefault();
//             if (this.parentElement.classList.contains("disabled")) return;
//
//             const text = this.textContent;
//             if (text === "Previous") currentPage--;
//             else if (text === "Next") currentPage++;
//             else currentPage = Number(text);
//             console.log("currentpage: ", currentPage)
//             renderProducts();
//         });
//     });
// }
//
// //add product
//
const $title = document.getElementById('add-book-title');
const $author = document.getElementById('add-book-author');
const $pages = document.getElementById('add-book-pages');
const $publisher = document.getElementById('add-book-publisher');
const $yearPublishing = document.getElementById('add-book-yeaPublishing');
const $category = document.getElementById('add-book-category');
const $price = document.getElementById('add-book-price');
const $discount = document.getElementById('add-book-discount');
const $quantity = document.getElementById('add-book-quantity');
const $description = document.getElementById('add-book-description');
const $imageName = document.getElementById('add-book-imageName');

// const $btn = document.getElementById('add-book-btn');
const $btnResetInput = document.getElementById('btn-reset-input');
$btnResetInput.onclick = resetInput;

//
// function renderHomeProduct(data){
//     if (data === undefined){
//         data = products;
//     }
//     data.sort((a, b) => a.createdAt - b.createdAt);
//     const listbody = document.getElementById("productHome");
//     listbody.innerHTML = "";
//     const productHomeItems = data.slice(0,12);
//     console.log("home item", productHomeItems);
//
//     productHomeItems.forEach((product,index) => {
//
//         const row= `
//                    <div class="col-lg-3 col-md-6">
//                 <div class="card p-3 mb-4">
//                     <a href="#" class="img-wrap text-center">
//                         <img class="img-fluid" src="img/200px.png">
//                     </a>
//                     <figcaption class="info-wrap mt-2">
//                         <a href="#" class="title">${product.name}</a>
//                         <div class="price mt-1 fw-bold">${product.price} ₫</div>
//                     </figcaption>
//                 </div>
//             </div> <!-- col.// -->
//                 `;
//
//         listbody.innerHTML += row;
//
//     });
//
// }
// document.querySelectorAll(".btn-insert-to-update-form").forEach(button => {
//     button.addEventListener("click", function (e) {
//         e.preventDefault();
//         let productId = Number(this.getAttribute("product-id"));
//         setTimeout(() => {
//             getInfoProduct(productId)
//             console.log("goi ham insert cho id ",productId);
//         },3000);
//
//         window.location.href = "/form-add-product";
//     })
// })

// function getInfoProduct(id) {
//     let result = products.find(product => product.id === id);
//     currentProductId = id;
//
//
//     $title.value = result.name;
//     $author.value = result.author;
//     $pages.value = result.pages;
//     $publisher.value = result.publisher ;
//     $yearPublishing.value = result.yearPublishing;
//     $category.value = result.categoryId;
//     $price.value = result.price;
//     $discount.value = result.discout;
//     $quantity.value = result.quantity;
//     $description.value = result.description;
//     $imageName.value = result.imageName;
//
//
//     // $btnCreateStudent.style.display = 'none';
//     // $btnUpdateStudent.style.display = 'inline-block';
//     console.log(result);
//
// }
// function addProduct(){
//     const product = {
//         name: $title.value,
//         price: $price.value,
//         discount: $discount.value,
//         quantity: $quantity.value,
//         author: $author.value,
//         pages: $pages.value,
//         publisher: $publisher.value,
//         yearPublishing: $yearPublishing.value,
//         description: $description.value,
//         imageName: $imageName.value,
//         categoryId: $category.value,
//     }
//
//     fetch('/add-product',{
//         method: 'POST',
//         headers: {
//             'Content-Type': 'application/json;charset=UTF-8',
//         },
//         body: JSON.stringify(product),
//     })
//     .then(response => {
//         if (!response.ok) {
//             throw new Error('Có lỗi khi thêm sản phẩm');
//         }
//         return response.json();
//     })
//         .then(data => {
//             alert(data.message);
//         })
//         .catch(error => {
//             console.log(error);
//             alert("có lỗi xảy ra khi thêm");
//         })
// }

function resetInput(){
    $title.value = '';
    $author.value = '';
    $pages.value = '';
    $publisher.value = '';
    $yearPublishing.value = '';
    $category.value = 'default';
    $price.value = '';
    $discount.value = '';
    $quantity.value = '';
    $description.value = '';
    $imageName.value = '';

}
//
// fetch("http://localhost:8080/products")
//     .then(response => response.json())
//     .then(data => console.log(data))
//     .catch(error => console.error("Lỗi khi gọi API:", error));
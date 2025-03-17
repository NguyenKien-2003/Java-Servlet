package vn.edu.t3h.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import vn.edu.t3h.dao.ProductRepository;
import vn.edu.t3h.entity.ProductEntity;
import vn.edu.t3h.model.ProductDTO;

import java.util.List;

@Repository("productHibernateRepositoryImpl")
public class ProductHibernateRepositoryImpl implements ProductRepository {
    private final SessionFactory sessionFactory;

    public ProductHibernateRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    /**
     hql ( hibernate query language)
     + ngôn ngữ truy vấn thao tác với cơ sở dữ liêuj thông quan entity, vì hibernate
     đã sử dụng orm để mapping các table thành các entity -> ngôn ngữ hql sẽ thao
     tác với các entity thay cho việc thao tác với table, nhưng bản chất nó sẽ tự động
     tạo ra các câu query bằng sql, nhưng ở tầng dưới
     + chỉ đáp ứng được các query sử dụng các toán tử đơn giản như join, having, order by,
     group by, anh, or, >, ==, <...
     + nhưng sẽ không đáp ứng được các câu query quá phứ tạp như gọi các funtion
     có sẵn của mysql
     native query:
     + các query sử dụng sql thông thường, trả về dữ liệu ra list object data
     + đáp ứng được mọi loại query cho dù phức tạp

     */

    @Override
    public List<ProductEntity> findAll() {
        Session session = sessionFactory.openSession();
        String hql = "from ProductEntity"; //
        Query<ProductEntity> query = session.createQuery(hql,ProductEntity.class);
        List<ProductEntity> repositories = query.getResultList();
        session.close();
        return repositories;
    }

    @Override
    public ProductEntity getProductById(int id) {
        Session session = sessionFactory.openSession();
        String hql = "from ProductEntity p where p.id = :id";
        Query<ProductEntity> query = session.createQuery(hql,ProductEntity.class);
        query.setParameter("id", id);
        ProductEntity repositories = query.getSingleResult();
        session.close();
        return repositories;
    }


    public List<ProductEntity> findByCondition(Double price, String bookTitle,String publisher,String categoryName){
        Session session = sessionFactory.openSession();
        StringBuilder sql = new StringBuilder("Select p from ProductEntity p join p.category c where 1 = 1");
        if (price != null) {
            sql.append("and p.price = :price");
        }
        if (StringUtils.hasText(bookTitle)){
            sql.append("and p.bookTitle = :bookTitle");
        }
        if (StringUtils.hasText(publisher)){
            sql.append("and p.publisher = :publisher");
        }

        if (StringUtils.hasText(categoryName)){
            sql.append("and c.categoryName = :categoryName");
        }
        Query<ProductEntity> query = session.createQuery(sql.toString(),ProductEntity.class);

        if (price != null) {
            query.setParameter("price", price);
        }
        if (StringUtils.hasText(bookTitle)){
            query.setParameter("bookTitle", bookTitle);
        }
        if (StringUtils.hasText(publisher)){
            query.setParameter("publisher", publisher);
        }
        if (StringUtils.hasText(categoryName)){
            query.setParameter("categoryName", categoryName);
        }
        List<ProductEntity> productEntities = query.getResultList();
        session.close();
        return productEntities;
    }

    @Override
    public void deleteProduct(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            String hql = "DELETE FROM ProductEntity p WHERE p.id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            int result = query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void addProduct(ProductDTO productDTO) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ProductEntity productEntity = new ProductEntity();
        productEntity.setAuthor(productDTO.getAuthor());
        productEntity.setBookTitle(productDTO.getBookTitle());
        productEntity.setDescription(productDTO.getDescription());
        productEntity.setDiscount(productDTO.getDiscount());
        productEntity.setGenre(productDTO.getGenre());
        productEntity.setImage(productDTO.getImage());
        productEntity.setPageCount(productDTO.getPageCount());
        productEntity.setPrice(productDTO.getPrice());
        productEntity.setPublicationYear(productDTO.getPublicationYear());
        productEntity.setPublisher(productDTO.getPublisher());
        productEntity.setStockQuantity(productDTO.getStockQuantity());
        session.save(productEntity);
        transaction.commit();
        session.close();
    }

    @Override
    public void updateProduct(ProductDTO product) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ProductEntity productEntity =session.get(ProductEntity.class, product.getId());
        if(productEntity!=null){
            productEntity.setAuthor(product.getAuthor());
            productEntity.setBookTitle(product.getBookTitle());
            productEntity.setDescription(product.getDescription());
            productEntity.setDiscount(product.getDiscount());
            productEntity.setGenre(product.getGenre());
            productEntity.setImage(product.getImage());
            productEntity.setPageCount(product.getPageCount());
            productEntity.setPrice(product.getPrice());
            productEntity.setPublicationYear(product.getPublicationYear());
            productEntity.setPublisher(product.getPublisher());
            productEntity.setStockQuantity(product.getStockQuantity());
            session.update(productEntity);
            transaction.commit();
            session.close();
        }
        System.out.println("khong tim thay san pham");
    }


}

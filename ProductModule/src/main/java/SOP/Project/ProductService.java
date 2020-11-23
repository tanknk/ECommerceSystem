package SOP.Project;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class ProductService {

	public String getLastId() throws InterruptedException, ExecutionException {
		Firestore db = FirestoreClient.getFirestore();
		ApiFuture<QuerySnapshot> future = db.collection("product").get();
		List<QueryDocumentSnapshot> documents = future.get().getDocuments();
		String id = "";
		for (DocumentSnapshot document : documents) {
			Product product = document.toObject(Product.class);
			id = product.getId();
		}
		return id;
	}

	public Product getProductbyId(String productId) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		DocumentReference productRef = dbFirestore.collection("product").document(productId);
		ApiFuture<DocumentSnapshot> future = productRef.get();
		DocumentSnapshot document = future.get();
		Product product = null;
		if (document.exists()) {
			product = document.toObject(Product.class);
			return product;
		} else {
			return null;
		}
	}

	public List<Product> getAllProduct() throws InterruptedException, ExecutionException {
		Firestore db = FirestoreClient.getFirestore();
		ApiFuture<QuerySnapshot> future = db.collection("product").get();
		List<QueryDocumentSnapshot> documents = future.get().getDocuments();
		List<Product> lists = new ArrayList<Product>();
		for (DocumentSnapshot document : documents) {
			Product product = document.toObject(Product.class);
			lists.add(product);
		}
		return lists;
	}

	public List<Product> getProductbyShopId(String shopId) throws InterruptedException, ExecutionException {
		Firestore db = FirestoreClient.getFirestore();
		ApiFuture<QuerySnapshot> future = db.collection("product").whereEqualTo("shop_id", shopId).get();
		List<QueryDocumentSnapshot> documents = future.get().getDocuments();
		List<Product> lists = new ArrayList<Product>();
		for (DocumentSnapshot document : documents) {
			Product product = document.toObject(Product.class);
			lists.add(product);
		}
		return lists;
	}

	public List<Option> getOptionbyProductId(String productId) throws InterruptedException, ExecutionException {
		Firestore db = FirestoreClient.getFirestore();
		DocumentReference productRef = db.collection("product").document(productId);
		ApiFuture<DocumentSnapshot> future = productRef.get();
		DocumentSnapshot document = future.get();
		Product product = null;
		if (document.exists()) {
			product = document.toObject(Product.class);
			return product.getOptions();
		} else {
			return null;
		}
	}

	public List<Category> getAllCategory() throws InterruptedException, ExecutionException {
		Firestore db = FirestoreClient.getFirestore();
		ApiFuture<QuerySnapshot> future = db.collection("category").get();
		List<QueryDocumentSnapshot> documents = future.get().getDocuments();
		List<Category> lists = new ArrayList<Category>();
		for (DocumentSnapshot document : documents) {
			Category category = document.toObject(Category.class);
			lists.add(category);
		}
		return lists;
	}

	public List<CategoryProduct> getAllCategoryProduct() throws InterruptedException, ExecutionException {
		Firestore db = FirestoreClient.getFirestore();
		ApiFuture<QuerySnapshot> future = db.collection("product_category").get();
		List<QueryDocumentSnapshot> documents = future.get().getDocuments();
		List<CategoryProduct> lists = new ArrayList<CategoryProduct>();
		for (DocumentSnapshot document : documents) {
			CategoryProduct catpro = document.toObject(CategoryProduct.class);
			lists.add(catpro);
		}
		return lists;
	}

	public String createProduct(Product product) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<DocumentReference> createProductRef = dbFirestore.collection("product").add(product);
		product.setId(createProductRef.get().getId());
		return saveProduct(product);
	}

	public String saveProduct(Product product) throws InterruptedException, ExecutionException {
		Firestore db = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> future = db.collection("product").document(product.getId()).set(product);
		return future.get().getUpdateTime().toString();
	}

	public String saveOption(Product product) throws InterruptedException, ExecutionException {
		Firestore db = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> future = db.collection("product").document(product.getId()).set(product);
		return future.get().getUpdateTime().toString();
	}

	public String saveCategory(Category category) throws InterruptedException, ExecutionException {
		Firestore db = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> future = db.collection("category").document(category.getId()).set(category);
		return future.get().getUpdateTime().toString();
	}

	public String deleteProduct(String productId) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> writeResult = dbFirestore.collection("product").document(productId).delete();
		return "Document with Patient ID " + productId + " has been deleted";
	}

	public String saveProductCategory(CategoryProduct catpro) throws InterruptedException, ExecutionException {
		Firestore db = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> future = db.collection("product_category").document(catpro.getId()).set(catpro);
		return future.get().getUpdateTime().toString();
	}

	public String deleteCategoryProduct(String productId) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<QuerySnapshot> future = dbFirestore.collection("product_category")
				.whereEqualTo("product_id", productId).get();
		List<QueryDocumentSnapshot> documents = future.get().getDocuments();
		for (DocumentSnapshot document : documents) {
			ApiFuture<WriteResult> writeResult = dbFirestore.collection("product_category").document(document.getId())
					.delete();
		}
		return "Document with Patient ID " + productId + " has been deleted";
	}

	public List<Product> getProductbyCategoryId(String categoryId) throws InterruptedException, ExecutionException {
		Firestore db = FirestoreClient.getFirestore();
		ApiFuture<QuerySnapshot> future = db.collection("product_category").whereEqualTo("category_id", categoryId)
				.get();
		ApiFuture<QuerySnapshot> future_product = db.collection("product").get();
		List<QueryDocumentSnapshot> documents = future.get().getDocuments();
		List<QueryDocumentSnapshot> documents_product = future_product.get().getDocuments();
		List<Product> lists = new ArrayList<Product>();
		for (DocumentSnapshot document : documents) {
			CategoryProduct catpro = document.toObject(CategoryProduct.class);
			for (DocumentSnapshot document_product : documents_product) {
				Product product = document_product.toObject(Product.class);
				if (catpro.getProduct_id().equals(product.getId())) {
					lists.add(product);
				}
			}
		}
		return lists;
	}
}

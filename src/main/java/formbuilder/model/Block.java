package formbuilder.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity(name = "block")
public class Block implements Serializable{
	@Id
    @GeneratedValue
	private Integer id;
	private String name;
	private String description;
	private boolean available; //block can be disabled
	@Column(name = "block_order")
    private int blockOrder;
	@ManyToOne
	private Page page; // parent page
	
	@OneToMany(mappedBy="block",cascade=CascadeType.ALL)
	@OrderBy("orderId")
	@Column(name="item")
	private List<Item> items;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public int getBlockOrder() {
		return blockOrder;
	}

	public void setBlockOrder(int blockOrder) {
		this.blockOrder = blockOrder;
	}
	
	
}

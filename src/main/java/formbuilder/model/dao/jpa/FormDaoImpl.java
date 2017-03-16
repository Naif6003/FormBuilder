package formbuilder.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import formbuilder.model.Block;
import formbuilder.model.Form;
import formbuilder.model.Page;
import formbuilder.model.dao.FormDao;

@Repository
public class FormDaoImpl implements FormDao {
	
	@PersistenceContext
    private EntityManager entityManager;

	@Override
	public Form getForm( Integer id ) {
		return entityManager.find(Form.class, id);
	}

	@Override
	public List<Form> getForms() {
		return entityManager.createQuery("from form order by id",Form.class).getResultList();
	}

	@Override
	@Transactional
	public Form saveForm(Form form) {
		return entityManager.merge(form);
	}

	public Page getPageById(Integer id){
		return entityManager.find(Page.class, id);
	}
	
	@Override
	@Transactional
	public Page savePage(Page page) {
		
		return entityManager.merge(page);
	}
	
	@Override
	@Transactional
	public Block saveBlock(Block block) {
		
		return entityManager.merge(block);
	}

	@Override
	public List<Block> getBlocks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Block getBlockById(Integer id) {
		return entityManager.find(Block.class, id);
	}

}

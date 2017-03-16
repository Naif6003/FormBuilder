package formbuilder.model.dao;

import java.util.List;

import formbuilder.model.Block;
import formbuilder.model.Form;
import formbuilder.model.Page;

public interface FormDao {
	
	Form getForm(Integer id);
	List<Form> getForms();
	Form saveForm(Form form);
	
	Page getPageById(Integer id);
	Page savePage(Page page);
	
	List<Block> getBlocks();
	Block getBlockById(Integer id);
	Block saveBlock(Block block);

}

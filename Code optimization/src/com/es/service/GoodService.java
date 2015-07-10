package com.es.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.es.dao.GoodDAO;
import com.es.model.Good;
import com.es.util.TransformUtil;
import com.es.vo.CategoryInfo;
import com.es.vo.GoodInfo;

public class GoodService {
	private GoodDAO goodDao = new GoodDAO();

	public List<GoodInfo> findAll(GoodInfo good , int page) {
		List<Good> goods = goodDao.selectAll(good , page);
		List<GoodInfo> infos = new ArrayList<GoodInfo> ();
		Iterator it = goods.iterator();
		while(it.hasNext()) {
			GoodInfo info = TransformUtil.goodTransform((Good)it.next());
			infos.add(info);
		}
		return infos;
	}

	public GoodInfo find(GoodInfo good) {
		Good g = goodDao.select(good);
		return TransformUtil.goodTransform(g);
	}

	public void create(GoodInfo good) {
		int id = goodDao.insert(good);
		good.setId(id);
		for(CategoryInfo c : good.getCategories()) {
			goodDao.insertGoodCategory(id, c.getId());
		}
	}

	public int countGood(GoodInfo good) {
		return goodDao.count(good.getCategories());
	}



}

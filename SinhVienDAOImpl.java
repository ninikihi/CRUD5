package com.dts.studentManager.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dts.studentManager.dto.SinhVienDTO;
import com.dts.studentManager.entity.SinhVienEntity;
import com.dts.studentManager.form.SearchForm;

@Repository
public class SinhVienDAOImpl implements SinhVienDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<SinhVienDTO> getSinhViens(SearchForm searchForm) {
		Session session = sessionFactory.getCurrentSession();

		Integer sinhvienId = searchForm.getSinhvienid();
		String sinhvienName = searchForm.getSinhvienname();
		String sql = "SELECT " + "sinhvienid AS maSV," + "sinhvienname AS tenSV," + "sinhvienns AS ngaysinh,"
				+ "sinhviendc AS diachi," + "sinhviencl AS lophoc " + " FROM SinhVienEntity ";
		if (sinhvienId != null && sinhvienName != null) {
			sql = sql + " WHERE ";
			sql = sql + " sinhvienid = :sinhvienId ";
			sql = sql + " AND sinhvienname LIKE :sinhvienName";
		}else 
			if(sinhvienId != null && sinhvienName == null) {
			sql = sql + " WHERE ";
			sql = sql + " sinhvienid = :sinhvienId ";
		}else 
			if (sinhvienId == null && sinhvienName != null) {
			sql = sql + " WHERE ";
			sql = sql + " sinhvienname LIKE :sinhvienName";
		}
		

		Query query = session.createQuery(sql);
		if (sinhvienId != null && sinhvienName != null) {
			
				query.setParameter("sinhvienId", sinhvienId);
				query.setParameter("sinhvienName", "%" + sinhvienName + "%");
				
		}else if(sinhvienId != null && sinhvienName == null) {
			
				query.setParameter("sinhvienId", sinhvienId);
				
		}else if (sinhvienId == null && sinhvienName !=null) {
			
				query.setParameter("sinhvienName", "%" + sinhvienName + "%");
			
		}

		List<SinhVienDTO> sinhvienDTOList = query.setResultTransformer(Transformers.aliasToBean(SinhVienDTO.class))
				.list();
		return sinhvienDTOList;
	}

	@Override
	public void deleteSinhVien(int id) {
		Session session = sessionFactory.getCurrentSession();
		SinhVienEntity book = session.byId(SinhVienEntity.class).load(id);
		session.delete(book);
	}

	@Override
	public void saveSinhVien(SinhVienEntity SinhVien) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(SinhVien);

		currentSession.saveOrUpdate(SinhVien);
		currentSession.save(SinhVien);
		currentSession.update(SinhVien);

	}

	@Override
	public SinhVienEntity getSinhVien(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		SinhVienEntity SinhVien = currentSession.get(SinhVienEntity.class, theId);
		return SinhVien;
	}
}

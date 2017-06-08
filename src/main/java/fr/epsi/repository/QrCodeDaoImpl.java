package fr.epsi.repository;

import fr.epsi.entity.model.QrCode;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Component
@Transactional
public class QrCodeDaoImpl implements QrCodeDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public QrCode findById(final Long id) {
        return sessionFactory.getCurrentSession().find(QrCode.class, id);
    }

    @Override
    public Optional<QrCode> findByHash(final String hash) {
        CriteriaBuilder cb = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<QrCode> cq = cb.createQuery(QrCode.class);
        Root<QrCode> root = cq.from(QrCode.class);
        cq.where(cb.equal(root.get("hash"), hash));
        return Optional.ofNullable(sessionFactory.getCurrentSession().createQuery(cq).getSingleResult());
    }

    @Override
    public QrCode save(final QrCode qrCode) {
        if (qrCode.getId() == null) // if the user is not persistent
            sessionFactory.getCurrentSession().save(qrCode);
        return (QrCode) sessionFactory.getCurrentSession().merge(qrCode);
    }

    @Override
    public void delete(final QrCode qrCode) {
        sessionFactory.getCurrentSession().remove(qrCode);
    }
}

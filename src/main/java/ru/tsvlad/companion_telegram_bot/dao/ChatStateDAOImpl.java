package ru.tsvlad.companion_telegram_bot.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.tsvlad.companion_telegram_bot.entity.ChatState;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ChatStateDAOImpl implements ChatStateDAO {
    private final EntityManager entityManager;

    @Autowired
    public ChatStateDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public ChatState getState(long chat_id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(ChatState.class, chat_id);
    }

    @Override
    public void saveState(ChatState state) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(state);
    }

    @Override
    public List<ChatState> getAllRows() {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("FROM ChatState", ChatState.class).getResultList();
    }

    @Override
    public void deleteRow(long chatID) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("DELETE FROM ChatState WHERE chat_id=:id");
        query.setParameter("id", chatID);
        query.executeUpdate();
    }
}

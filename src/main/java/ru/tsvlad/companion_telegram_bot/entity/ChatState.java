package ru.tsvlad.companion_telegram_bot.entity;

import ru.tsvlad.companion_telegram_bot.state.State;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "chat_state")
public class ChatState {
    @Id
    @Column(name = "chat_id")
    private long chatID;
    @Column(name = "")
    private State state;

    public ChatState() {
    }

    public ChatState(long chatID, State state) {
        this.chatID = chatID;
        this.state = state;
    }

    public long getChatID() {
        return chatID;
    }

    public void setChatID(long chatID) {
        this.chatID = chatID;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}

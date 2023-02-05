package impl;

import com.google.inject.Inject;
import entity.Card;
import export.CardDao;
import export.CardService;
import jakarta.persistence.EntityTransaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CardServiceImpl implements CardService {


    static final private Logger LOGGER = LogManager.getLogger(CardService.class);

    private CardDao cardDao;

    private EntityTransaction entityTransaction;

    public CardServiceImpl() {
        super();
    }
    @Inject
    public CardServiceImpl(CardDao cardDao, EntityTransaction entityTransaction) {
        this();
        this.cardDao = cardDao;
        this.entityTransaction = entityTransaction;
    }

    @Override
    public void createCard(){
        entityTransaction.begin();
        Card category = new Card();
        cardDao.create(category);
        entityTransaction.commit();
    }

}
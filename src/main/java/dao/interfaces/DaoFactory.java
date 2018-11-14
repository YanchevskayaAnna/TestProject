package dao.interfaces;

import dao.SQLDao.SQLDaoFactory;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract ICarriageDAO createCarriageDao();
    public abstract ITrainDAO createTrainDao();

    public static DaoFactory getInstance(){
            if( daoFactory == null ){
                synchronized (DaoFactory.class){
                    if(daoFactory==null){
                        DaoFactory temp = new SQLDaoFactory();
                        daoFactory = temp;
                    }
                }
            }
            return daoFactory;
        }
}

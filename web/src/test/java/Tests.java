import dao.BusinessDao;
import model.Business;
import service.BusinessService;

public class Tests {
    public static void main(String[] args) {
        Business prevBusiness = BusinessService.getInstance().getById(11l);
        System.out.println(prevBusiness);

      /*  Business currBusiness;
        for (int i=0; i<20; i++) {
            currBusiness = Business.builder()
                    .name(prevBusiness.getName() + "a")
                    .unp(prevBusiness.getUnp() + i)
                    .build();
            prevBusiness = BusinessDao.getInstance().create(currBusiness);
       }*/
    }
}

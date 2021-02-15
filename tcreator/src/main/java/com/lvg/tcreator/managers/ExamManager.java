package com.lvg.tcreator.managers;

import com.lvg.tcreator.models.NdtMethod;
import com.lvg.tcreator.models.Order;
import com.lvg.tcreator.models.TestTypes;
import com.lvg.tcreator.persistence.models.ExamDB;
import com.lvg.tcreator.persistence.models.ExamTicketDB;
import com.lvg.tcreator.persistence.models.OrderDB;
import com.lvg.tcreator.persistence.models.QuestionDB;
import com.lvg.tcreator.persistence.services.QuestionDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

import static com.lvg.tcreator.config.R.ExamProperties.*;

@Component
public class ExamManager {

    @Autowired
    private QuestionDBService questionDBService;

    private NdtMethod ndtMethod;
    private String orderNumber;
    private LocalDate orderDate;
    private int ticketCont;
    private final List<TestTypes> testTypesList = new ArrayList<>();
    private OrderDB orderDB;

    protected ExamManager(){}

    public void initOrderDB(Order orderDao){
        this.ndtMethod = orderDao.getNdtMethod();
        this.orderNumber = orderDao.getNumber();
        this.orderDate = orderDao.getDate();
        this.ticketCont = orderDao.getVariantCount();
        this.testTypesList.clear();
        this.testTypesList.addAll(orderDao.getTestTypes());
        createOrderDB();
    }

    public OrderDB getOrderDB(Order orderDao){
        initOrderDB(orderDao);
        return orderDB;
    }

    public List<ExamTicketDB> getAllExamTickets(){
        List<ExamTicketDB> examTicketDBList = new ArrayList<>();
        orderDB.getExams().forEach(examDB -> {
            examTicketDBList.addAll(examDB.getTickets());
        });
        return Collections.unmodifiableList(examTicketDBList);
    }

    private void createOrderDB() {
        orderDB = new OrderDB();
        orderDB.setNumber(orderNumber);
        orderDB.setDate(orderDate);
        orderDB.setNdtMethod(ndtMethod);
        createExamDBList();
    }

    private void createExamDBList() {
        testTypesList.forEach(testType ->{
            ExamDB examDB = new ExamDB();
            examDB.setTestTypes(testType);
            orderDB.addExam(examDB);

            for (int i = 0; i < ticketCont; i++) {
                ExamTicketDB examTicketDB = createExamTicket(examDB);
                examTicketDB.setTicketVariant(i+1);
                examDB.addExamTicket(examTicketDB);
            }
        });
    }


    private ExamTicketDB createExamTicket(ExamDB examDB) {
        List<QuestionDB> questions = questionDBService.findByNdtMethodTestTypes(examDB.getNdtMethod(), examDB.getTestTypes());
        Set<QuestionDB> randomQuestions = getRandomQuestionsFromList(questions, getQuestionsCount(examDB.getTestTypes()));
        ExamTicketDB examTicketDB = new ExamTicketDB();
        examTicketDB.setExam(examDB);
        randomQuestions.forEach(examTicketDB::addQuestion);
        return examTicketDB;
    }

    private Set<QuestionDB> getRandomQuestionsFromList(List<QuestionDB> questList, int countQuestions) {
        if (questList.size() < countQuestions)
            throw new IllegalArgumentException("Question base size less than count of questions.");
        Set<QuestionDB> result = new TreeSet<>(Comparator.comparing(QuestionDB::getQuestionNumber));

        while (result.size() < countQuestions) {
            int index = generateIndex(questList.size());
            result.add(questList.get(index));
        }
        return result;
    }

    private int generateIndex(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }

    private int getQuestionsCount(TestTypes testType) {
        int sectorsCount = getSectorsCount();

        if (testType == TestTypes.TOTAL_TEST) {
            if (ndtMethod == NdtMethod.RT || ndtMethod == NdtMethod.UT)
                return TOTAL_TEST_UT_RT_QUESTIONS_COUNT;
            else
                return TOTAL_TEST_MT_VT_PT_QUESTIONS_COUNT;
        } else if (testType == TestTypes.SPEC_TEST) {
            return SPEC_TEST_QUESTIONS_COUNT;
        } else if (sectorsCount == 3) {
            return SPEC_TEST_THREE_SECTOR_QUESTIONS_COUNT;
        } else if (sectorsCount == 2) {
            return SPEC_TEST_TWO_SECTOR_QUESTIONS_COUNT;
        } else
            return SPEC_TEST_ONE_SECTOR_QUESTIONS_COUNT;
    }

    private int getSectorsCount() {
        if (testTypesList.contains(TestTypes.SPEC_6_SECTOR_TEST) &&
                testTypesList.contains(TestTypes.SPEC_7_SECTOR_TEST) &&
                testTypesList.contains(TestTypes.SPEC_8_SECTOR_TEST))
            return 3;
        else if (testTypesList.contains(TestTypes.SPEC_6_SECTOR_TEST) && testTypesList.contains(TestTypes.SPEC_7_SECTOR_TEST))
            return 2;
        else if (testTypesList.contains(TestTypes.SPEC_6_SECTOR_TEST) && testTypesList.contains(TestTypes.SPEC_8_SECTOR_TEST))
            return 2;
        else if (testTypesList.contains(TestTypes.SPEC_7_SECTOR_TEST) && testTypesList.contains(TestTypes.SPEC_8_SECTOR_TEST))
            return 2;
        else
            return 1;

    }


}

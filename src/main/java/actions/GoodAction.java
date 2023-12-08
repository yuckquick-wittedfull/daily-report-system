package actions;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;

import actions.views.EmployeeConverter;
import actions.views.EmployeeView;
import actions.views.ReportConverter;
import actions.views.ReportView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.MessageConst;
import models.Employee;
import models.Good;
import models.Report;
import services.GoodService;
import services.ReportService;

public class GoodAction extends ActionBase{

    private GoodService goodservice;
    private ReportService reportservice;

    /**
     * メソッドを実行する
     */
    @Override
    public void process() throws ServletException, IOException {

        goodservice = new GoodService();
        reportservice = new ReportService();

        //メソッドを実行
        invoke();
        goodservice.close();
        reportservice.close();
    }

    public void create() throws ServletException, IOException {

        models.Good g = new models.Good();

        String rep_id = request.getParameter("rep_id");
        ReportView rv = reportservice.findOne(toNumber(rep_id));
        g.setReport(ReportConverter.toModel(rv));

        EmployeeView ev = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);
        g.setEmployee(EmployeeConverter.toModel(ev));

        LocalDateTime createdAt = LocalDateTime.now();
        g.setCreatedAt(createdAt);

        goodservice.create(g);

        putSessionScope(AttributeConst.FLUSH, MessageConst.I_REGISTERED.getMessage());

      //一覧画面にリダイレクト
        redirect(ForwardConst.ACT_REP,ForwardConst.CMD_INDEX);
}

    public void destroy() throws ServletException, IOException {

        String rep_id = request.getParameter("rep_id");
        Report rep = ReportConverter.toModel(reportservice.findOne(toNumber(rep_id)));
        Employee emp = EmployeeConverter.toModel((EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP));

        Good g = goodservice.findOne(emp, rep);

        goodservice.destroy(g);

        putSessionScope(AttributeConst.FLUSH, MessageConst.I_DELETED.getMessage());

        //一覧画面にリダイレクト
        redirect(ForwardConst.ACT_REP,ForwardConst.CMD_INDEX);
        }


    }


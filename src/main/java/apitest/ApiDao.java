package apitest;

import apitest.model.ApiTest;
import apitest.model.Assertinfo;
import apitest.model.EumAction;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import apitest.assertimpl.AssertActionI;
import apitest.assertimpl.CodeAssertImpl;
import apitest.assertimpl.ContextAssertImpl;
import apitest.assertimpl.JsonTextAssertImpl;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.testng.Assert;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ApiDao {

    public static final String URL="jdbc:mysql://172.17.49.51:3306/Task?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";
    public static final String UN="root";
    public static final String PWD="root";

    public static QueryRunner set_Database(String url,String UN,String PWD) throws SQLException {
        setConnectInfo(url,UN,PWD);
        return run=new QueryRunner(ds);
    }
    public static void setConnectInfo(String url,String UN,String PWD) {
        ds = new MysqlDataSource();
        ds.setURL(url);
        ds.setUser(UN);
        ds.setPassword(PWD);
    }

    public static List<AssertActionI> assertList;
    public static MysqlDataSource ds;
    public static QueryRunner run;

    public ApiDao() {
        try {
            assertList=new LinkedList<>();
            assertList.add(new CodeAssertImpl());
            assertList.add(new JsonTextAssertImpl<ApiTest,HttpResponse>());
            assertList.add(new ContextAssertImpl<ApiTest,HttpResponse>());
            run= set_Database(URL,UN,PWD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ApiDao ad=new ApiDao();
        List<ApiTest> ss=ad.getApiTestCase("select * from apidata where id=237");
        System.out.println("CASES: "+ss.size());
        for(ApiTest at:ss){
            HttpResponse<String> response =ad.getResponse(at);
            List<Assertinfo> ais=ad.getAssertInfo("select * from assertinfo where caseid="+at.getId());
            System.out.println("ASSERTS: "+ais.size());
            for(Assertinfo ai:ais){
                assert_plus(ai,ad,response);
            }
        }


    }

    public static void assert_plus(Assertinfo ai,Object request,Object response){
        for(AssertActionI al: assertList){
            if(al.getFlag().toString().equals(ai.getAssert_type().trim())){
                System.out.println("Check Case: "+al.getFlag().toString()+" -- "+ai.getAssert_action() +" = "+ai.getAssert_value());
                al.api_Assert(ai,request,response);
                break;
            }
        }


    }


    public HttpResponse<String> getResponse(ApiTest at){
        HttpResponse<String> response =null;
        System.out.println(at.getUrl());
        try {
            if(at.getMethod().equals(EumAction.Method.GET.toString())){
                response= sendGet(at.getUrl());
            }else if(at.getMethod().equals(EumAction.Method.POST.toString())){
                response= sendPost(at.getUrl(),at.getBody());
            }
            System.out.println(response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(response==null){
            Assert.fail("Response is null -- "+at.getUrl());
        }
        return response;
    }

    public  HttpResponse<String> sendPost(String url, String bodyParams) {
        HttpResponse<String> response =null;
        try {
         response = Unirest.post(url)
              .body(bodyParams).asString();
        } catch (UnirestException e) {
            System.out.println(e.getMessage());
        }
        return response;
    }

    public HttpResponse<String> sendGet(String url) {
        HttpResponse<String> response =null;
        try {
           response = Unirest.get(url)
                    .asString();
        } catch (UnirestException e) {
            System.out.println(e.getMessage());
        }
        return response;
    }

    public List<ApiTest> getApiTestCase(String sql){
        List<ApiTest> list=null;
        try {
            list  = (List) run.query(sql, new BeanListHandler(ApiTest.class));
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public List<Assertinfo> getAssertInfo(String sql){
        List<Assertinfo> list=null;
        try {
            list  = (List) run.query(sql, new BeanListHandler(Assertinfo.class));
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }


}

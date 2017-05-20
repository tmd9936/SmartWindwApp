package device1;

import android.os.AsyncTask;
import android.util.Log;

import com.kt.smcp.gw.ca.comm.exception.SdkException;
import com.kt.smcp.gw.ca.gwfrwk.adap.stdsys.sdk.tcp.BaseInfo;
import com.kt.smcp.gw.ca.gwfrwk.adap.stdsys.sdk.tcp.IMTcpConnector;
import com.kt.smcp.gw.ca.gwfrwk.adap.stdsys.sdk.tcp.LogIf;
import com.kt.smcp.gw.ca.util.IMUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DeviceTask extends AsyncTask<Void, Void, Void> {

    private static final String TAG = DeviceTask.class.getSimpleName();

    private Map<String, Double> rows = new HashMap<String, Double>();

    public DeviceTask(Map<String, Double> rows) {
        this.rows = rows;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... params) {

        // IoTMakers 연동 설정 정보
        BaseInfo info = new BaseInfo();
        // 접속 IP, Port 설정
        info.setIp("220.90.216.90");
        info.setPort(10020);
        // 디바이스상세정보-> Gateway 연결 ID를 입력한다.
        info.setExtrSysId("OPEN_TCP_001PTL001_1000003581");
        // 디바이스상세정보-> 디바이스 아이디를 입력한다.
        info.setDeviceId("tmd993D1494308925360");
        // 디바이스상세정보-> 디바이스 패스워드를 입력한다.
        info.setPassword("nugycoi4m");

        // IoTMakers 연동 TCP Connector 생성
        IMTcpConnector connector = new IMTcpConnector();
        try {
            connector.activate(new LogIf(), info, (long) 3000);

            long transId = IMUtil.getTransactionLongRoundKey4();

            Log.d(TAG, rows.toString());
            // 계측 데이터 HashMap 객체로 전송한다. key는 센싱태그 명 value는 계측값을 넣는다.
            connector.requestNumColecDatas(rows, new Date(), transId);

            connector.deactivate();;

        } catch (SdkException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
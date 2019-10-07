package sqlMap;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class MybatisManager {
	private MybatisManager() {}; //생성자 
	
	// SqlSession객체를 만들어내는 SqlSessionFactory참조 변수
	// mybatis query를 수행하는 객체
	// mybatis의 진행순서 SqlSessionFactoryBuilder->sqlSessionFactory->sqlSession
	private static SqlSessionFactory instance;
	
	public static SqlSessionFactory getInstance() {
		Reader reader = null; //이거 선언했으면 if 하고 바로 넣을 수 있다
		if(reader == null){
			//읽기용
			try {
				//SqlMapConfig.xml(mybatis환경설정)파일의 정보를 읽어오는 코드
				String resource = "sqlMap/SqlMapConfig.xml";
				reader = Resources.getResourceAsReader(resource);
				
				//sqlSessionFactory객체 생성 instance 자바 DB 연결해주는 역할을 함, 실제 객체화 시켜줌
				instance = new SqlSessionFactoryBuilder().build(reader);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(reader != null) reader.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		return instance;
	}

}

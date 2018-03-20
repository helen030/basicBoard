package demos.common.web.tutorial.persistence;

import demos.common.web.tutorial.MemberVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@Repository
public class MemberDAOImpl implements MemberDAO {

    @Inject
    SqlSession sqlSession;

    private static final String NAMESPACE = "demos.common.web.mappers.tutorial.memberMapper";
//    private static final String NAMESPACE = "com.doubles.mvcboard.mappers.tutorial.MemberMapper";

    @Override
    public String getTime() {
        return sqlSession.selectOne(NAMESPACE + ".getTime");
    }

    @Override
    public void insertMember(MemberVO memberVO) {
        sqlSession.insert(NAMESPACE + ".insertMember", memberVO);
    }

    @Override
    public MemberVO readMember(String userid) throws Exception {
        return sqlSession.selectOne(NAMESPACE + ".selectMember", userid);
    }

    @Override
    public MemberVO readWithPW(String userid, String userpw) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userid", userid);
        paramMap.put("userpw", userpw);
        return sqlSession.selectOne(NAMESPACE + ".readWithPW", paramMap);
    }
}

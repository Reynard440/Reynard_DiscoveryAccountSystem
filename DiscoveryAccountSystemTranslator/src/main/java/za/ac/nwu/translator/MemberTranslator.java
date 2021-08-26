package za.ac.nwu.translator;

import org.springframework.stereotype.Component;
import za.ac.nwu.domain.dto.MemberDto;

import java.util.List;

@Component
public interface MemberTranslator {
    List<MemberDto> getAllMembers();
}

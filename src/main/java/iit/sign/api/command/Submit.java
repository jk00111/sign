package iit.sign.api.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Submit {

    private final long id;

    private final long deciderId;

}

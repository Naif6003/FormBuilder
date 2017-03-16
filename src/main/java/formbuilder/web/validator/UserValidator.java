package formbuilder.web.validator;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import formbuilder.model.User;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports( Class<?> clazz )
    {
        return User.class.isAssignableFrom( clazz );
    }

    @Override
    public void validate( Object target, Errors errors )
    {
        User user = (User) target;

        if( !StringUtils.hasText( user.getFirstName() ) )
            errors.rejectValue( "username", "error.firstname.empty" );

        if( !StringUtils.hasText( user.getLastName() ) )
            errors.rejectValue( "username", "error.lastname.empty" );
        
    }

}

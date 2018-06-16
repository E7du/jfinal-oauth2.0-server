package cn.zhucongqi.oauth2.base.validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.jfinal.kit.StrKit;

import cn.zhucongqi.oauth2.clientcredentials.ClientCredentials;
import cn.zhucongqi.oauth2.consts.Consts;
import cn.zhucongqi.oauth2.consts.OAuthError;
import cn.zhucongqi.oauth2.exception.OAuthProblemException;
import cn.zhucongqi.oauth2.kit.OAuthExceptionHandleKit;

/**
 * OAuth Request Validator
 * @author Jobsz [zcq@zhucongqi.cn]
 * @version
 * @param <T>
 */
public abstract class OAuthValidator {

	private HttpServletRequest request = null;
    protected List<String> requiredParams = new ArrayList<String>();
    protected HashMap<String, String> paramDefaultValues = new HashMap<String, String>();
    
    private ClientCredentials customClientCredentialsValidator = null;
    
    public OAuthValidator(HttpServletRequest request) {
    	this.request = request;
    	this.requiredParams.add(Consts.AuthConsts.AUTH_SCOPE);
    	this.requiredParams.add(Consts.AuthConsts.AUTH_STATE);
        this.paramDefaultValuesValidation();
    }
    
    public void setCustomClientCredentialsValidator(ClientCredentials customClientCredentialsValidator) {
    	this.customClientCredentialsValidator = customClientCredentialsValidator;
    }

    /**
     * param default value validation 
     */
    public abstract void paramDefaultValuesValidation();
    
    /**
     * validate method
     * @throws OAuthProblemException
     */
    protected void validateMethod() throws OAuthProblemException {
        String method = this.request.getMethod();
        if (!method.equals(Consts.HttpMethod.GET) && !method.equals(Consts.HttpMethod.POST)) {
            throw OAuthProblemException.error(OAuthError.CodeResponse.INVALID_REQUEST)
                .description("Method not correct.");
        }
    }

    /**
     * validate content type
     * @throws OAuthProblemException
     */
    protected void validateContentType() throws OAuthProblemException {
        String contentType = this.request.getContentType();
        final String expectedContentType = Consts.ContentType.URL_ENCODED;
        if (!OAuthExceptionHandleKit.hasContentType(contentType, expectedContentType)) {
            throw OAuthExceptionHandleKit.handleBadContentTypeException(expectedContentType);
        }
    }

    /**
     * validate parameter
     * @throws OAuthProblemException
     */
    protected void validateRequiredParameters() throws OAuthProblemException {
        final Set<String> missingParameters = new HashSet<String>();
        for (String requiredParam : requiredParams) {
            String val = this.request.getParameter(requiredParam);
            if (StrKit.isBlank(val)) {
                missingParameters.add(requiredParam);
            }
        }
        if (!missingParameters.isEmpty()) {
            throw OAuthExceptionHandleKit.handleMissingParameters(missingParameters);
        }
    }
    
    /**
     * validate paramter values
     * @throws OAuthProblemException
     */
    protected void validateRequiredParameterValues() throws OAuthProblemException {
    	final Set<String> keys = paramDefaultValues.keySet();
    	for (String key : keys) {
			String param = this.request.getParameter(key);
			String mustValue = paramDefaultValues.get(key);
			if (StrKit.isBlank(param) 
					|| (StrKit.notBlank(param) && !mustValue.equals(param))) {
				throw OAuthExceptionHandleKit.handleInvalidValueException(param, mustValue);
			}
		}
    }
    
    /**
     * validateClientCredentials
     * @throws OAuthProblemException
     */
    private void validateClientCredentials()
			throws OAuthProblemException {
    	// validate parameters missing
		Set<String> missingParameters = new HashSet<String>();
		
		String client_id = this.request.getParameter(Consts.AuthConsts.AUTH_CLIENT_ID);
		if (StrKit.isBlank(client_id)) {
			missingParameters.add(Consts.AuthConsts.AUTH_CLIENT_ID);
		}
		
		String client_secret = this.request.getParameter(Consts.AuthConsts.AUTH_CLIENT_SECRET);
		if (StrKit.isBlank(client_secret)) {
			missingParameters.add(Consts.AuthConsts.AUTH_CLIENT_SECRET);
		}

		//check missing or not
		if (!missingParameters.isEmpty()) {
			throw OAuthExceptionHandleKit.handleMissingParameters(missingParameters);
		}
		
		//validate parameters validation
		this.customClientCredentialsValidator.validateClientCredentials(this.request);
	}
    
    private String scope = "";
    private String state = "";
	private String clientId = null;
	private String clientSecret = null;
	private String code = null;
    
    public String getScope() {
		return scope;
	}

	private void setScope(String scope) {
		this.scope = scope;
	}

	public String getState() {
		return state;
	}

	private void setState(String state) {
		this.state = state;
	}
	
	public String getClientId() {
		return clientId;
	}

	private void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	private void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getCode() {
		return code;
	}

	private void setCode(String code) {
		this.code = code;
	}
	
	/**
     * get client parameters
     * @param request
     */
    protected void getClientParameters() {
    	// client and secret
    	String clientId = this.request.getParameter(Consts.AuthConsts.AUTH_CLIENT_ID);
    	if (StrKit.notBlank(clientId)) {
        	this.setClientId(clientId);	
		}
    	
    	String clientSecret = this.request.getParameter(Consts.AuthConsts.AUTH_CLIENT_SECRET);
    	if (StrKit.notBlank(clientSecret)) {
        	this.setClientSecret(clientSecret);	
		}
    	
    	String code = this.request.getParameter(Consts.AuthConsts.AUTH_CODE);
    	if (StrKit.notBlank(code)) {
        	this.setCode(code);	
		}
    	
    	String state = this.request.getParameter(Consts.AuthConsts.AUTH_STATE);
		if (StrKit.notBlank(state)) {
			this.setState(state);
		}
		
		String scope = this.request.getParameter(Consts.AuthConsts.AUTH_SCOPE);
		if (StrKit.notBlank(scope)) {
			this.setScope(scope);
		}
    }

    /**
     * validate request
     * 
     * @throws OAuthProblemException
     */
    public void validate() throws OAuthProblemException {
        this.validateContentType();
        this.validateMethod();
        this.validateRequiredParameters();
        this.validateRequiredParameterValues();
        
        this.getClientParameters();
        //client credentials
		this.validateClientCredentials();
    }
}

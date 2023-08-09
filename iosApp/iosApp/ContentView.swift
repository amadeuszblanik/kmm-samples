import SwiftUI
import KMMViewModelSwiftUI
import shared

extension ContentView {
    class ViewModel: shared.SignInViewModel {}
}

struct ContentView: View {
    @StateViewModel var viewModel = ViewModel()

    var body: some View {
        VStack {
            List {
                Section(header: Text("Input")) {
                    HStack {
                        Text("Email")

                        TextField("Email here", text: Binding(get: {
                            viewModel.email.value as? String ?? ""
                        }, set: { value in
                            viewModel.setEmail(email: value)
                        }))
                    }

                    HStack {
                        Text("Password")

                        SecureField("Type here", text: Binding(get: {
                            viewModel.password.value as? String ?? ""
                        }, set: { value in
                            viewModel.setPassword(password: value)
                        }))
                    }
                }
                
                Section(header: Text("Output")) {
                    HStack {
                        Text("Email")

                        Text(viewModel.email.value as! String)
                    }
                    HStack {
                        Text("Password")

                        Text(viewModel.password.value as! String)
                    }
                }
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}

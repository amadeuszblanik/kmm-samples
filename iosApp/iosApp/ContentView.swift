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
                            viewModel.email
                        }, set: { value in
                            viewModel.setEmail(email: value)
                        }))
                    }

                    HStack {
                        Text("Password")

                        SecureField("Type here", text: Binding(get: {
                            viewModel.password
                        }, set: { value in
                            viewModel.setPassword(password: value)
                        }))
                    }
                }
                
                Section(header: Text("Output")) {
                    HStack {
                        Text("Email")

                        Text(viewModel.email)
                    }
                    HStack {
                        Text("Password")

                        Text(viewModel.password)
                    }
                    HStack {
                        Text("State")

                        Text(viewModel.state.name)
                    }
                    HStack {
                        Text("Error message")

                        Text(viewModel.errorMessage ?? "—")
                    }
                }
                
                Section(header: Text("Action")) {
                    Button {
                        Task {
                            try await viewModel.signIn()
                        }
                    } label: {
                        Text("Sign in")
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
